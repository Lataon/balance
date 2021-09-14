package com.gmail.elbaglikov.schedule;

import com.gmail.elbaglikov.bean.User;
import com.gmail.elbaglikov.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class SendMessageRunner implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(SendMessageRunner.class);

    private final UserService userService;
    private final int duration;
    private final int balanceMaxValue;
    private final ApplicationContext context;

    public SendMessageRunner(UserService userService, int duration, int balanceMaxValue, ApplicationContext context) {
        this.userService = userService;
        this.duration = duration;
        this.balanceMaxValue = balanceMaxValue;
        this.context = context;
    }

    @Override
    public void run() {
        try {
            List<User> users = userService.getByBalance(balanceMaxValue);
            SupportSender sender = new SupportSenderAMQPImpl();
            users.forEach(e -> sender.send(e, context));
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
