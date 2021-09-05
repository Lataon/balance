package com.gmail.elbaglikov.schedule;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;

public class SupportSenderAMQPImpl implements  SupportSender {

    public <T> void send(T object, ApplicationContext context) {
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        template.convertAndSend("myqueue", String.valueOf(object));
    }
}
