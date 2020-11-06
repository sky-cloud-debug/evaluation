package com.evaluation.system.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Setter
@Entity
@Data
@Getter
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
}
