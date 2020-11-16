package com.evaluation.system.domain.ExtraEntity;

import lombok.ToString;

@ToString
public class VerifyHonor {

    private String number;

    private String name;

    private String classmajor;

    private String studentHonor;

    private String year;

    private int state;
    //姓名
    private String reason;

    public VerifyHonor() {
    }

    public VerifyHonor(String number, String name, String classmajor, String studentHonor, String year, int state, String reason) {
        this.number = number;
        this.name = name;
        this.classmajor = classmajor;
        this.studentHonor = studentHonor;
        this.year = year;
        this.state = state;
        this.reason = reason;
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

    public String getClassmajor() {
        return classmajor;
    }

    public void setClassmajor(String classmajor) {
        this.classmajor = classmajor;
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
