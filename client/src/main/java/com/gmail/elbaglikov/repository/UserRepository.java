package com.gmail.elbaglikov.repository;

import com.gmail.elbaglikov.bean.User;

import java.util.List;

public interface UserRepository {
    Iterable<User> getAll();
    List<User> getByBalance(int k);
    void save(User user);
    void saveAll(Iterable<User> balances);
    void deleteAll();
}
