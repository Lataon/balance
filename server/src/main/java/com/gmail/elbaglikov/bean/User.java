package com.gmail.elbaglikov.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import static com.gmail.elbaglikov.Constants.DELIMITER;

@Entity
@Table(name = "users")
public class User {
    @Id
    @XmlElement
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "tariff", nullable = false)
    private String tariff;
    @Column(name = "balance", nullable = false)
    private int balance;

    public User() {
    }

    public User(int id, String name, String tariff, int balance) {
        this.id = id;
        this.name = name;
        this.tariff = tariff;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return id + DELIMITER + name + DELIMITER + tariff + DELIMITER + balance + DELIMITER;
    }
}
