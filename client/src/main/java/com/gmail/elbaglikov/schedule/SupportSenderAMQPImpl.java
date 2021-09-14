package com.gmail.elbaglikov.schedule;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;

import static com.gmail.elbaglikov.Constants.QUEUE_NAME;

public class SupportSenderAMQPImpl implements SupportSender {

    public <T> void send(T object, ApplicationContext context) {
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend(QUEUE_NAME, String.valueOf(object));
    }
}
