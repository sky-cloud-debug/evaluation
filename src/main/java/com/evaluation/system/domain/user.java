package com.evaluation.system.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
public class user {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String number;

    private String password;

    private Integer permissions;

    public user(String number, String password, Integer permissions) {
        this.number = number;
        this.password = password;
        this.permissions = permissions;
    }

    public user() {
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", passwordId=" + password +
                ", permissions=" + permissions +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }
}
