package com.acsousa.creditcard.mscreditappraiser.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acsousa.creditcard.mscreditappraiser.application.exception.ClientDataNotFoundException;
import com.acsousa.creditcard.mscreditappraiser.application.exception.MicroserviceCommunicationException;
import com.acsousa.creditcard.mscreditappraiser.application.exception.RequestIssuanceCardException;
import com.acsousa.creditcard.mscreditappraiser.domain.models.ApprovedCard;
import com.acsousa.creditcard.mscreditappraiser.domain.models.Card;
import com.acsousa.creditcard.mscreditappraiser.domain.models.ClientCard;
import com.acsousa.creditcard.mscreditappraiser.domain.models.ClientData;
import com.acsousa.creditcard.mscreditappraiser.domain.models.ClientSituation;
import com.acsousa.creditcard.mscreditappraiser.domain.models.IssuanceCardData;
import com.acsousa.creditcard.mscreditappraiser.domain.models.IssuanceCardProtocol;
import com.acsousa.creditcard.mscreditappraiser.domain.models.ReturnApprovedCards;
import com.acsousa.creditcard.mscreditappraiser.infra.clients.CardResourceClient;
import com.acsousa.creditcard.mscreditappraiser.infra.clients.ClientResourceClient;
import com.acsousa.creditcard.mscreditappraiser.infra.mqueue.IssuanceCardPublisher;

import feign.FeignException.FeignClientException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {
    private final ClientResourceClient clientResourceClient;
    private final CardResourceClient cardResourceClient;
    private final IssuanceCardPublisher issuanceCardPublisher;

    public ClientSituation getClientSituation(String cpf) throws ClientDataNotFoundException, MicroserviceCommunicationException {        
        try {
            ResponseEntity<ClientData> clientDataResponse = clientResourceClient.getClientByCpf(cpf);
            ResponseEntity<List<ClientCard>> cardResponse = cardResourceClient.getCardByClient(cpf);

            return ClientSituation                
                    .builder()
                    .client(clientDataResponse.getBody())
                    .cards(cardResponse.getBody())
                    .build();
            
        } catch (FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new ClientDataNotFoundException(cpf);
            }
            throw new MicroserviceCommunicationException(e.getMessage(), status);
        }
    }

    public ReturnApprovedCards doAppraisal(String cpf, Long income) throws ClientDataNotFoundException, MicroserviceCommunicationException {
        try {
            ResponseEntity<ClientData> clientDataResponse = clientResourceClient.getClientByCpf(cpf);
            ResponseEntity<List<Card>> cardDataResponse = cardResourceClient.getCardsIncomeLessThanEqual(income);

            List<Card> cards = cardDataResponse.getBody();
            var approvedCards = cards.stream().map(card -> {
                ClientData clientData = clientDataResponse.getBody();
                BigDecimal basicLimit = card.getBasicLimit();
                BigDecimal age = BigDecimal.valueOf(clientData.getAge());
                BigDecimal factor = age.divide(BigDecimal.valueOf(10));
                BigDecimal approvedLimit = factor.multiply(basicLimit);

                ApprovedCard approvedCard = new ApprovedCard();
                approvedCard.setName(card.getName());
                approvedCard.setFlag(card.getFlag());
                approvedCard.setApprovedLimit(approvedLimit);

                return approvedCard;
            }).collect(Collectors.toList());

            return new ReturnApprovedCards(approvedCards);
            
        } catch (FeignClientException e) {
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new ClientDataNotFoundException(cpf);
            }
            throw new MicroserviceCommunicationException(e.getMessage(), status);
        }
    }

    public IssuanceCardProtocol requestIssuanceCard(IssuanceCardData data){
        try {
            issuanceCardPublisher.requestCard(data);
            var protocol = UUID.randomUUID().toString();
            return new IssuanceCardProtocol(protocol);            
        } catch (Exception e) {
            throw new RequestIssuanceCardException(e.getMessage());
        }
    }
}
