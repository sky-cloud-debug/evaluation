package com.evaluation.system.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class user {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String number;

    private Integer passwordId;

    private Integer permissions;

    public user(String number, Integer passwordId, Integer permissions) {
        this.number = number;
        this.passwordId = passwordId;
        this.permissions = permissions;
    }

    public user() {
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
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

    public Integer getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(Integer passwordId) {
        this.passwordId = passwordId;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", passwordId=" + passwordId +
                ", permissions=" + permissions +
                '}';
    }
}
