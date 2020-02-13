package com.evaluation.system.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@ToString
public class testGroup {
    //学号
    @Id
    @Column(name = "number")
    private String number;

    private String name;

    private String classMajor;

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

    public String getClassMajor() {
        return classMajor;
    }

    public void setClassMajor(String classMajor) {
        this.classMajor = classMajor;
    }

    @Override
    public String toString() {
        return "testGroup{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", classMajor='" + classMajor + '\'' +
                '}';
    }
}
