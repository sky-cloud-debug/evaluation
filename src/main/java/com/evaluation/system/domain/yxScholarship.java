package com.evaluation.system.domain;

import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Proxy(lazy = false)
@Entity
@Data
@Setter
@Getter
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
