package com.evaluation.system.domain;

import org.hibernate.annotations.Proxy;

@Proxy(lazy = false)
public class AllqtAwards {
    //学号
    private String number;

    //姓名
    private String name;
    //专业年级班级
    private String class_major;
    //奖学金名称
    private String bonus_name;

    public AllqtAwards(String number, String name, String class_major, String bonus_name) {
        this.number = number;
        this.name = name;
        this.class_major = class_major;
        this.bonus_name = bonus_name;
    }

    public AllqtAwards() {
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

    public String getBonus_name() {
        return bonus_name;
    }

    public void setBonus_name(String bonus_name) {
        this.bonus_name = bonus_name;
    }

    @Override
    public String toString() {
        return "AllqtAwards{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", class_major='" + class_major + '\'' +
                ", bonus_name='" + bonus_name + '\'' +
                '}';
    }
}
