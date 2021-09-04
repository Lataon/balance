package com.gmail.elbaglikov.web;

import com.gmail.elbaglikov.bean.UserList;
import com.gmail.elbaglikov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/users",
            headers="Accept=application/json, application/xml")
    public @ResponseBody UserList getUsers (){
        return new UserList(userService.getUsers());
    }
}
