package com.evaluation.system.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@ToString
public class basic {

    //学号
    private Integer number;

    //姓名
    private String name;

    //性别
    private String sex;

    //专业年级班级
    private String classMajor;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
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
