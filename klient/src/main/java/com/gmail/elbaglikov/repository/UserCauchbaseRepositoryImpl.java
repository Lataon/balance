package com.gmail.elbaglikov.repository;

import com.gmail.elbaglikov.bean.User;
import com.gmail.elbaglikov.repository.cauchbase.UserDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCauchbaseRepositoryImpl implements UserRepository {

    private final UserDataJpaRepository repository;

    @Autowired
    public UserCauchbaseRepositoryImpl(UserDataJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<User> getAll() {
        return repository.findAll();
    }

    @Override
    public List<User> getByBalance(int k) {
        return repository.findByBalanceLessThan(k);
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void saveAll(Iterable<User> balances) {
        repository.saveAll(balances);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
