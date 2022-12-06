package com.acsousa.creditcard.mscreditappraiser.infra.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.acsousa.creditcard.mscreditappraiser.domain.models.IssuanceCardData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IssuanceCardPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueIssuanceCard;

    public void requestCard(IssuanceCardData data) throws JsonProcessingException{
        String json = convertToJson(data);
        rabbitTemplate.convertAndSend(queueIssuanceCard.getName(), json);
    }

    private String convertToJson(IssuanceCardData data) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(data);
        return json;
    }
    
}