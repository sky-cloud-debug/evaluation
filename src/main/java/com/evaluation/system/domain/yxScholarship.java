package com.evaluation.system.domain;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
@Proxy(lazy = false)
@Entity
@Data
public class yxScholarship {

    @Id
    private int id;
    private String number;  //学号
    private int scholarshipLevel; //奖学金等级
    private String cardNumber;  //卡号
    private String year;
    private int state; //审核状态
    String reason; //驳回原因
    public yxScholarship() {
    }

    public yxScholarship(String number, int scholarshipLevel, String cardNumber, String year, int state, String reason) {
        this.number = number;
        this.scholarshipLevel = scholarshipLevel;
        this.cardNumber = cardNumber;
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

    @Override
    public String toString() {
        return "yxScholarship{" +
                "number='" + number + '\'' +
                ", scholarshipLevel='" + scholarshipLevel + '\'' +
                ", cardNumber=" + cardNumber +
                ", state='" + state + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
