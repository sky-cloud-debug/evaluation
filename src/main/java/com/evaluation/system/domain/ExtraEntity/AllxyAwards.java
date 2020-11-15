package com.evaluation.system.domain.ExtraEntity;

public class AllxyAwards {
    //学号
    private String number;
    //姓名
    private String name;
    //奖学金等级
    private int scholarshipLevel;

    public AllxyAwards() {
    }

    public AllxyAwards(String number, String name,int scholarshipLevel) {
        this.number = number;
        this.name = name;
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

    public int getScholarshipLevel() {
        return scholarshipLevel;
    }

    public void setScholarshipLevel(int scholarshipLevel) {
        this.scholarshipLevel = scholarshipLevel;
    }

    @Override
    public String toString() {
        return "AllxyAwards{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", scholarshipLevel='" + scholarshipLevel + '\'' +
                '}';
    }
}
