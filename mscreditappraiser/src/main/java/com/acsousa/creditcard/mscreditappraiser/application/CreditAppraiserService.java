package com.acsousa.creditcard.mscreditappraiser.application;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.acsousa.creditcard.mscreditappraiser.application.exception.ClientDataNotFoundException;
import com.acsousa.creditcard.mscreditappraiser.application.exception.MicroserviceCommunicationException;
import com.acsousa.creditcard.mscreditappraiser.domain.models.ClientCard;
import com.acsousa.creditcard.mscreditappraiser.domain.models.ClientData;
import com.acsousa.creditcard.mscreditappraiser.domain.models.ClientSituation;
import com.acsousa.creditcard.mscreditappraiser.infra.clients.CardResourceClient;
import com.acsousa.creditcard.mscreditappraiser.infra.clients.ClientResourceClient;

import feign.FeignException.FeignClientException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditAppraiserService {
    private final ClientResourceClient clientResourceClient;
    private final CardResourceClient cardResourceClient;

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
}