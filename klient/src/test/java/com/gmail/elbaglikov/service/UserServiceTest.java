package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.bean.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value= {"/spring/couchbase.xml", "/spring/spring-app.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @After
    public void deleteAllTest() {
        userService.deleteAll();
    }

    @Test
    public void createTest() {
        User user = new User(1,1000);
        userService.save(user);
        user = new User(2,1001);
        userService.save(user);
        user = new User(3,1002);
        userService.save(user);
        userService.save(user);
        System.out.println(userService.getAll());
    }

    @Test
    public void saveXmlTest() {
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><users><user><id>100000</id><balance>1000</balance><name>Ivan Ivanov</name><tariff>fly on the internet</tariff></user><user><id>100001</id><balance>2000</balance><name>Ivan Alexeenko</name><tariff>fly on the internet</tariff></user><user><id>100002</id><balance>6000</balance><name>Alex Ivanov</name><tariff>fly on the internet</tariff></user><user><id>100003</id><balance>3000</balance><name>Ivan Ivanov</name><tariff>fly on the internet</tariff></user><user><id>100004</id><balance>2000</balance><name>Alex Alexeenko</name><tariff>all inclusive</tariff></user></users>";
        userService.saveXml(xmlStr);
        List<User> expected = Arrays.asList(new User(100000, 1000),
                new User (100001, 2000),
                new User(100002, 6000),
                new User (100003,3000),
                new User(100004, 2000));
        List<User> actual = StreamSupport.stream(userService.getAll().spliterator(), false).collect(Collectors.toList());

        assertThat(actual, is(expected));
    }

    @Test
    public void getByBalanceTest() {
        List<User> all = Arrays.asList(new User(100000, 1000),
                new User (100001, 2000),
                new User(100002, 6000),
                new User (100003,3000),
                new User(100004, 2000));
        userService.saveAll(all);
        List<User> expected = Arrays.asList(new User(100000, 1000),
                new User (100001, 2000),
                new User(100004, 2000),
                new User (100003,3000));
        List<User> actual = userService.getByBalance(3000);
        assertThat(actual, is(expected));
    }
}