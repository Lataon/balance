import com.gmail.elbaglikov.schedule.GetDataFromServerRunner;
import com.gmail.elbaglikov.schedule.SendMessageRunner;
import com.gmail.elbaglikov.service.UserService;
import com.gmail.elbaglikov.web.UserClient;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Runner implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            LOGGER.debug("start application");
            ResourceBundle rb = ResourceBundle.getBundle("application");
            int m = Integer.parseInt(rb.getString("key.m"));
            int n = Integer.parseInt(rb.getString("key.n"));
            int k = Integer.parseInt(rb.getString("key.k"));
            String exitWord = rb.getString("key.exit");

            appCtx.load( "spring/couchbase.xml",
                    "spring/spring-app.xml",
                    "spring/spring-mvc.xml",
                    "spring/amqp.xml");
            appCtx.refresh();
            UserClient userClient = appCtx.getBean(UserClient.class);
            UserService userService = appCtx.getBean(UserService.class);

            GetDataFromServerRunner getDataFromServerRunner = new GetDataFromServerRunner(userClient, n);
            Thread dateFromServerGetter = new Thread(getDataFromServerRunner);

            SendMessageRunner sendMessageRunner = new SendMessageRunner(userService, m, k, appCtx);
            Thread messageSender = new Thread(sendMessageRunner);
            dateFromServerGetter.start();
            messageSender.start();
            while (true) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String line = bufferedReader.readLine();
                if (line.equals(exitWord)) {
                    sendMessageRunner.setFlag(false);
                    getDataFromServerRunner.setFlag(false);
                    userService.deleteAll();
                    break;
                }
            }
            LOGGER.debug("exit from application");
        } catch (IOException e) {
            LOGGER.error("exit from application", e);
        }
    }

    @Override
    public void run() {

    }
}
