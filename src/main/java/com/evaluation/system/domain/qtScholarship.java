package com.evaluation.system.domain;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Proxy(lazy = false)
@Data
@Entity
public class qtScholarship {

    @Id
    @Column(name="number")

    String number; //学号

    String bonus_name; //奖学金名称

    Integer card_number; //卡号

    String state;  //审核状态

    String reason;  //驳回原因

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBonus_name() {
        return bonus_name;
    }

    public void setBonus_name(String bonus_name) {
        this.bonus_name = bonus_name;
    }

    public Integer getCardNumber() {
        return card_number;
    }

    public void setCardNumber(Integer cardNumber) {
        this.card_number = cardNumber;
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

    public qtScholarship(String number, String bonus_name, Integer card_number, String state, String reason) {
        this.number = number;
        this.bonus_name = bonus_name;
        this.card_number = card_number;
        this.state = state;
        this.reason = reason;
    }

    public qtScholarship() {
    }

    @Override
    public String toString() {
        return "qtScholarship{" +
                "number='" + number + '\'' +
                ", bonus_name='" + bonus_name + '\'' +
                ", cardNumber=" + card_number +
                ", state='" + state + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
