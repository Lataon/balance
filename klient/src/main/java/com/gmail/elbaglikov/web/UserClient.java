package com.gmail.elbaglikov.web;

import com.gmail.elbaglikov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class UserClient {

    private final UserService userService;
    private final RestTemplate restTemplate;

    @Autowired
    public UserClient(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    public void getXML () {
        //TODO
        final String URI = "http://localhost:8080/server_war_exploded/users";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                URI,
                HttpMethod.GET,
                entity,
                String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            userService.saveXml(response.getBody());
        }
    }

}
