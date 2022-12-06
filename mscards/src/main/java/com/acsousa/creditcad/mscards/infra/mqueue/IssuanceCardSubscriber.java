package com.acsousa.creditcad.mscards.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.acsousa.creditcad.mscards.domain.Card;
import com.acsousa.creditcad.mscards.domain.ClientCard;
import com.acsousa.creditcad.mscards.domain.IssuanceCardData;
import com.acsousa.creditcad.mscards.infra.repository.CardRepository;
import com.acsousa.creditcad.mscards.infra.repository.ClientCardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IssuanceCardSubscriber {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientRepository;

    @RabbitListener(queues = "${mq.queues.issuance-cards}")
    public void receiveIssueRequest (@Payload String payload){
        System.out.println(payload);

        try {
            var mapper = new ObjectMapper();
            
            IssuanceCardData data = mapper.readValue(payload, IssuanceCardData.class);
            Card card = cardRepository.findById(data.getId()).orElseThrow();
            
            ClientCard client = new ClientCard();
            client.setCard(card);
            client.setCpf(data.getCpf());
            client.setLimitReleased(data.getLimitReleased());
            
            clientRepository.save(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}