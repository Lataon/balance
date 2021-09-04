package com.gmail.elbaglikov.repository;

import com.gmail.elbaglikov.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class UserDataJpaRepositoryImpl implements UserRepository{
    private final UserDataJPARepository repository;

    @Autowired
    public UserDataJpaRepositoryImpl(UserDataJPARepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getUsers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).
                collect(Collectors.toList());
    }
}
