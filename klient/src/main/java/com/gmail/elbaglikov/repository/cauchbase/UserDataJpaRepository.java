package com.gmail.elbaglikov.repository.cauchbase;

import com.gmail.elbaglikov.bean.User;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDataJpaRepository extends CrudRepository<User, Integer> {

    @View(viewName = "getByBalance")
    @Query
    List<User> findByBalanceLessThan(int k);
}
