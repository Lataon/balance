package com.gmail.elbaglikov.service;

import com.gmail.elbaglikov.bean.User;
import com.gmail.elbaglikov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.getUsers();
    }
}
