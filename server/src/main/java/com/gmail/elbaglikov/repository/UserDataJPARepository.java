package com.gmail.elbaglikov.repository;

import com.gmail.elbaglikov.bean.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDataJPARepository extends CrudRepository<User, Integer> {

}
