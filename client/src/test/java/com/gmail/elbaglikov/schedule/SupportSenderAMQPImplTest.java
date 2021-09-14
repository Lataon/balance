package com.gmail.elbaglikov.schedule;

import com.gmail.elbaglikov.bean.User;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value= {"/spring/amqp.xml"})
public class SupportSenderAMQPImplTest extends TestCase {

    @Autowired
    public AmqpTemplate template;

    @Autowired
    ApplicationContext context;

    @Test
    public void testSend() {
        User expected = new User(100000, 1000);
        SupportSender supportSender = new SupportSenderAMQPImpl();
        supportSender.send(expected, context);
        String actualStr = (String) template.receiveAndConvert("myqueue");
        assert actualStr != null;
        User actual = new User (actualStr.split(";"));
        assertEquals(expected, actual);
    }
}