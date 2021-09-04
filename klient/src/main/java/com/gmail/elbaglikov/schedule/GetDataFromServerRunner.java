package com.gmail.elbaglikov.schedule;

import com.gmail.elbaglikov.web.UserClient;
import org.apache.log4j.Logger;


public class GetDataFromServerRunner implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(GetDataFromServerRunner.class);

    private final UserClient userClient;
    private final int n;
    private boolean flag = true;

    public GetDataFromServerRunner(UserClient userClient, int n) {
        this.userClient = userClient;
        this.n = n;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        while (flag) {
            try {
                userClient.getXML();
                Thread.sleep(n);
            }
            catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
