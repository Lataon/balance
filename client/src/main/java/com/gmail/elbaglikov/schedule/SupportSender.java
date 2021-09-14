package com.gmail.elbaglikov.schedule;

import org.springframework.context.ApplicationContext;

public interface SupportSender {
    <T> void send(T object, ApplicationContext context);
}
