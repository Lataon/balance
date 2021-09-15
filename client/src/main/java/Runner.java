import com.gmail.elbaglikov.exceptions.QueryServerException;
import com.gmail.elbaglikov.exceptions.TransformXMLException;
import com.gmail.elbaglikov.schedule.GetDataFromServerRunner;
import com.gmail.elbaglikov.schedule.SendMessageRunner;
import com.gmail.elbaglikov.service.UserService;
import com.gmail.elbaglikov.web.UserClient;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Runner {
    private static final Logger LOGGER = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
            try(GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
                LOGGER.info("start application");
                ResourceBundle rb = ResourceBundle.getBundle("application");
                final int M_PARAMETER = Integer.parseInt(rb.getString("key.m"));
                int N_PARAMETER = Integer.parseInt(rb.getString("key.n"));
                int K_PARAMETER = Integer.parseInt(rb.getString("key.k"));

                appCtx.load(
                        "spring/spring-app.xml",
                        "spring/spring-mvc.xml",
                        "spring/couchbase.xml",
                        "spring/amqp.xml");
                appCtx.refresh();
                UserClient userClient = appCtx.getBean(UserClient.class);
                UserService userService = appCtx.getBean(UserService.class);

                try {
                    GetDataFromServerRunner getDataFromServerRunner = new GetDataFromServerRunner(userClient, N_PARAMETER);
                    Thread dateFromServerGetter = new Thread(getDataFromServerRunner);

                    SendMessageRunner sendMessageRunner = new SendMessageRunner(userService, M_PARAMETER, K_PARAMETER, appCtx);
                    Thread messageSender = new Thread(sendMessageRunner);

                    dateFromServerGetter.start();
                    messageSender.start();
                    dateFromServerGetter.join();
                    messageSender.join();
                } catch (TransformXMLException | QueryServerException | InterruptedException e) {
                    LOGGER.error(e.getMessage());
                }  finally {
                    userService.deleteAll();
                }
            }
    }

}
