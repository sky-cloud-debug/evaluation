package com.evaluation.system.domain.ExtraEntity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ChangePwd {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String oldpassword;

    private String newpassword1;

    private  String newpassword2;

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String pwd) {
        this.oldpassword = pwd;
    }

    public String getNewpassword1() {
        return newpassword1;
    }

    public void setNewpassword1(String pwd) {
        this.newpassword1 = pwd;
    }

    public String getNewpassword2() {
        return newpassword2;
    }

    public void setNewpassword2(String pwd) {
        this.newpassword2 = pwd;
    }

    @Override
    public String toString() {
        return null;
    }
}
