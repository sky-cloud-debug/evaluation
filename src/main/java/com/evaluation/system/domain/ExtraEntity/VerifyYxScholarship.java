package com.evaluation.system.domain.ExtraEntity;

public class VerifyYxScholarship {

    private String number;  //学号
    private String name;
    private String classMajor;
    private int scholarshipLevel; //奖学金等级
    private String cardNumber;  //卡号
    private String year;
    private String reason; //驳回原因

    public VerifyYxScholarship() {
    }

    public VerifyYxScholarship(String number, String name, String classMajor, int scholarshipLevel, String cardNumber, String year, String reason) {
        this.number = number;
        this.name = name;
        this.classMajor = classMajor;
        this.scholarshipLevel = scholarshipLevel;
        this.cardNumber = cardNumber;
        this.year = year;
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

    public String getClassMajor() {
        return classMajor;
    }

    public void setClassMajor(String classMajor) {
        this.classMajor = classMajor;
    }

    public int getScholarshipLevel() {
        return scholarshipLevel;
    }

    public void setScholarshipLevel(int scholarshipLevel) {
        this.scholarshipLevel = scholarshipLevel;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
