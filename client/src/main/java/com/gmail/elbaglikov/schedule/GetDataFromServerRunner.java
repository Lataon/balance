package com.gmail.elbaglikov.schedule;

import com.gmail.elbaglikov.web.UserClient;
import org.apache.log4j.Logger;


public class GetDataFromServerRunner implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(GetDataFromServerRunner.class);

    private final UserClient userClient;
    private final int duration;

    public GetDataFromServerRunner(UserClient userClient, int duration) {
        this.userClient = userClient;
        this.duration = duration;
    }

    @Override
    public void run() {
        while (true) {
            try {
                userClient.getXML();
                Thread.sleep(duration);
            }
            catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
