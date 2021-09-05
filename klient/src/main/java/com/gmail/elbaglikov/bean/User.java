package com.gmail.elbaglikov.bean;


import com.couchbase.client.java.repository.annotation.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.Objects;

import static com.gmail.elbaglikov.Constants.DELIMETER_CSV;

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

    public User (String[] csv) {
        this(Integer.parseInt(csv[0]), balanceStrToInt(csv[1]));
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

    private static int balanceStrToInt(String str) {
        String[] arr = str.split("\\.");
        return Integer.parseInt(arr[0])*100 + Integer.parseInt(arr[1]);
    }

    @Override
    public String toString() {
        return id + DELIMETER_CSV + UserToStr();
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
