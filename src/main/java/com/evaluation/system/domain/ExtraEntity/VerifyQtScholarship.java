package com.evaluation.system.domain.ExtraEntity;

import lombok.ToString;

@ToString
public class VerifyQtScholarship {

    private String number; //学号
    private  String name;
    private String classMajor;
    private String bonus_name; //奖学金名称
    private String card_number; //卡号
    private String year;//审核状态
    private String reason;  //驳回原因

    public VerifyQtScholarship() {
    }

    public VerifyQtScholarship(String number, String name, String classMajor, String bonus_name, String card_number, String year, String reason) {
        this.number = number;
        this.name = name;
        this.classMajor = classMajor;
        this.bonus_name = bonus_name;
        this.card_number = card_number;
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

    public String getBonus_name() {
        return bonus_name;
    }

    public void setBonus_name(String bonus_name) {
        this.bonus_name = bonus_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
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
