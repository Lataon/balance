package com.gmail.elbaglikov.schedule;

import com.gmail.elbaglikov.bean.User;
import com.gmail.elbaglikov.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class SendMessageRunner implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(SendMessageRunner.class);

    private final UserService userService;
    private final int m;
    private final int k;
    private final ApplicationContext context;
    private boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public SendMessageRunner(UserService userService, int m, int k, ApplicationContext context) {
        this.userService = userService;
        this.m = m;
        this.k = k;
        this.context = context;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                List<User> users = userService.getByBalance(k);
                Thread.sleep(m);
                SupportSender sender = new SupportSenderAMQPImpl();
                users.forEach(e -> sender.send(e, context));
            }
            catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

}
