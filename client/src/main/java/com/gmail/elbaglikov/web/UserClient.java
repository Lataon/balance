package com.gmail.elbaglikov.web;

import com.gmail.elbaglikov.exceptions.QueryServerException;
import com.gmail.elbaglikov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.gmail.elbaglikov.Constants.URI;

public class UserClient {

    private final UserService userService;
    private final RestTemplate restTemplate;

    @Autowired
    public UserClient(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    public void getXML () {
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
        } else throw  new QueryServerException("Server isn't available or check URI");
    }

}
