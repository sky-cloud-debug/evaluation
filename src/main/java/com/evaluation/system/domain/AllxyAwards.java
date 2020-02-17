package com.evaluation.system.domain;

public class AllxyAwards {
    //学号
    private String number;

    //姓名
    private String name;
    //专业年级班级
    private String class_major;
    //奖学金等级
    private String scholarshipLevel;

    public AllxyAwards() {
    }

    public AllxyAwards(String number, String name, String class_major, String scholarshipLevel) {
        this.number = number;
        this.name = name;
        this.class_major = class_major;
        this.scholarshipLevel = scholarshipLevel;
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

    public String getClass_major() {
        return class_major;
    }

    public void setClass_major(String class_major) {
        this.class_major = class_major;
    }

    public String getScholarshipLevel() {
        return scholarshipLevel;
    }

    public void setScholarshipLevel(String scholarshipLevel) {
        this.scholarshipLevel = scholarshipLevel;
    }

    @Override
    public String toString() {
        return "AllxyAwards{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", class_major='" + class_major + '\'' +
                ", scholarshipLevel='" + scholarshipLevel + '\'' +
                '}';
    }
}
