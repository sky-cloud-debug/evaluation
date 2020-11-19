package com.evaluation.system.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@Data
@ToString
@Proxy(lazy = false)
public class qtScholarship {

    @Id
    private int id;
    private String number; //学号
    private String bonus_name; //奖学金名称
    private String card_number; //卡号
    private String year;
    private int state;  //审核状态
    private String reason;  //驳回原因

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getBonus_name() {
        return bonus_name;
    }

    public String getCard_number() {
        return card_number;
    }

    public String getYear() {
        return year;
    }

    public int getState() {
        return state;
    }

    public String getReason() {
        return reason;
    }

    public qtScholarship() {
    }

    public qtScholarship(String number, String bonus_name, String card_number, String year, int state, String reason) {
        this.number = number;
        this.bonus_name = bonus_name;
        this.card_number = card_number;
        this.year = year;
        this.state = state;
        this.reason = reason;
    }

    public qtScholarship(int id, String number, String bonus_name, String card_number, String year, int state, String reason) {
        this.id = id;
        this.number = number;
        this.bonus_name = bonus_name;
        this.card_number = card_number;
        this.year = year;
        this.state = state;
        this.reason = reason;
    }

}
