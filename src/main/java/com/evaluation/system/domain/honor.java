package com.evaluation.system.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
public class honor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "number")
    //学号
    private String number;

    //姓名
    private String name;

    //学生荣誉
    //团干部、三好学生等
    private String studentHonor;

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

    public String getStudentHonor() {
        return studentHonor;
    }

    public void setStudentHonor(String studentHonor) {
        this.studentHonor = studentHonor;
    }

    @Override
    public String toString() {
        return "honor{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", studentHonor='" + studentHonor + '\'' +
                '}';
    }
}
