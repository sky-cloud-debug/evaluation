package com.evaluation.system.domain;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Data
@Proxy(lazy = false)
public class temporarybasic {

    //学号
    @Id
    @Column(name = "number")
    private String number;

    //姓名
    private String name;

    //性别
    private String sex;

    //政治面貌
    private String political;

    //职务
    private String duty;

    //专业年级班级
    private String classMajor;

    public temporarybasic(String number, String name, String sex, String political, String duty, String classMajor) {
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.political = political;
        this.duty = duty;
        this.classMajor = classMajor;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public temporarybasic() {
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
        return "{" +
                "\"number\""+":" + "\""+number+ "\""   +
                ",\"name\""+":" + "\""+name+ "\"" +
                ", \"sex\""+":" + "\""+sex+ "\"" +
                ", \"political\""+":" + "\""+political+ "\""+
                ", \"duty\""+":" +"\""+duty+ "\"" +
                ", \"classMajor\""+":" + "\""+classMajor+ "\"" +
                "}";
    }
}

