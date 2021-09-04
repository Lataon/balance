package com.gmail.elbaglikov.schedule;

import com.gmail.elbaglikov.bean.User;
import com.gmail.elbaglikov.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

public class SendMessageRunner implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(SendMessageRunner.class);

    private final UserService userService;
    private final int m;
    private final int k;
    private boolean flag = true;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public SendMessageRunner(UserService userService, int m, int k) {
        this.userService = userService;
        this.m = m;
        this.k = k;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                List<User> users = userService.getByBalance(k);
                //TODO
                Thread.sleep(m);
                LOGGER.info("write to log => " + users);
            }
            catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

    }
}
