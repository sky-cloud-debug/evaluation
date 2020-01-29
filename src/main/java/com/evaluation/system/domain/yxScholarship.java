package com.evaluation.system.domain;

import lombok.Data;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@Data
public class yxScholarship {


    @Id
    @Column(name="number")
    String number;

    String scholarshipLevel;

    Integer cardNumber;

    String state;

    String reason;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getScholarshipLevel() {
        return scholarshipLevel;
    }

    public void setScholarshipLevel(String scholarshipLevel) {
        this.scholarshipLevel = scholarshipLevel;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
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
