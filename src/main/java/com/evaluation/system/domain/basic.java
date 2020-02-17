package com.evaluation.system.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Data
@Proxy(lazy = false)
public class basic {

    //学号
    @Id
    @Column(name = "number")
    private String number;

    //姓名
    private String name;

    //性别
    private String sex;

    //专业年级班级
    private String classMajor;

    public basic(String number, String name, String sex, String classMajor) {
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.classMajor = classMajor;
    }

    public basic() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClassMajor() {
        return classMajor;
    }

    public void setClassMajor(String classMajor) {
        this.classMajor = classMajor;
    }

    @Override
    public String toString() {
        return "basic{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", classMajor='" + classMajor + '\'' +
                '}';
    }
}
