package com.gmail.elbaglikov.bean;


import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.Objects;

@Document
public class User {

    @Id
    private int id;
    @Field
    private int balance;

    public User() {
    }

    public User(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String UserToStr() {
        return String.format("%d.%02d", balance /100, balance %100);
    }

    @Override
    public String toString() {
        return id + ";" + UserToStr();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                balance == user.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance);
    }
}
