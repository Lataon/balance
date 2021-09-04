package com.gmail.elbaglikov.web;

import com.gmail.elbaglikov.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@ContextConfiguration(value= {"/spring/couchbase.xml", "/spring/spring-app.xml", "/spring/spring-mvc.xml"})
public class UserClientTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UserClient userClient;

    private MockRestServiceServer mockServer;

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @After
    public void destroy() {
        userService.deleteAll();
    }

    @Test
    public void getXMLTest() throws IOException, URISyntaxException {
        final String uri = "http://localhost:8080/users";
        String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><users><user><id>100000</id><balance>1000</balance><name>Ivan Ivanov</name><tariff>fly on the internet</tariff></user><user><id>100001</id><balance>2000</balance><name>Ivan Alexeenko</name><tariff>fly on the internet</tariff></user><user><id>100002</id><balance>6000</balance><name>Alex Ivanov</name><tariff>fly on the internet</tariff></user><user><id>100003</id><balance>3000</balance><name>Ivan Ivanov</name><tariff>fly on the internet</tariff></user><user><id>100004</id><balance>2000</balance><name>Alex Alexeenko</name><tariff>all inclusive</tariff></user></users>";
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(uri)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(xmlStr));
        userClient.getXML();
        mockServer.verify();
    }
}