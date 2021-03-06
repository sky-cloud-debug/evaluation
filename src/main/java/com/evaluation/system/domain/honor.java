package com.evaluation.system.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
public class honor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    //学号
    private String number;

    private String studentHonor;

    private String year;

    private int state;
    //姓名
    private String reason;

    //学生荣誉
    //团干部、三好学生等


    public honor() {
    }

    public honor(String number, String studentHonor, String year, int state, String reason) {
        this.number = number;
        this.studentHonor = studentHonor;
        this.year = year;
        this.state = state;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStudentHonor() {
        return studentHonor;
    }

    public void setStudentHonor(String studentHonor) {
        this.studentHonor = studentHonor;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
