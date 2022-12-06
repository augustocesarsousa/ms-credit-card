package com.acsousa.creditcad.mscards.infra.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class InssuaceCardSubscriber {

    @RabbitListener(queues = "${mq.queues.issuance-cards}")
    public void receiveIssueRequest (@Payload String payload){
        System.out.println(payload);
    }
}