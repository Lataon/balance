package com.gmail.elbaglikov.repository;

import com.gmail.elbaglikov.bean.User;

import java.util.List;

public interface UserRepository {
    List<User> getUsers();
}
