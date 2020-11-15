package com.evaluation.system.domain.ExtraEntity;

public class ShowStu {
    //学号
    private String number;

    //姓名
    private String name;

    //性别
    private String sex;

    //专业年级班级
    private String class_major;

    //道德素养
    private Double moral;

    //智育成绩
    private Double wisdom;

    //身心素养
    private Double heart;

    //科技人文
    private Double technology;

    //总分
    private Double total_count;

    public ShowStu(String number, String name, String sex, String class_major, Double moral, Double wisdom, Double heart, Double technology, Double total_count) {
        this.number = number;
        this.name = name;
        this.sex = sex;
        this.class_major = class_major;
        this.moral = moral;
        this.wisdom = wisdom;
        this.heart = heart;
        this.technology = technology;
        this.total_count = total_count;
    }

    public ShowStu() {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClass_major() {
        return class_major;
    }

    public void setClass_major(String class_major) {
        this.class_major = class_major;
    }

    public Double getMoral() {
        return moral;
    }

    public void setMoral(Double moral) {
        this.moral = moral;
    }

    public Double getWisdom() {
        return wisdom;
    }

    public void setWisdom(Double wisdom) {
        this.wisdom = wisdom;
    }

    public Double getHeart() {
        return heart;
    }

    public void setHeart(Double heart) {
        this.heart = heart;
    }

    public Double getTechnology() {
        return technology;
    }

    public void setTechnology(Double technology) {
        this.technology = technology;
    }

    public Double getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Double total_count) {
        this.total_count = total_count;
    }

    @Override
    public String toString() {
        return "ShowStu{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", class_major='" + class_major + '\'' +
                ", moral=" + moral +
                ", wisdom=" + wisdom +
                ", heart=" + heart +
                ", technology=" + technology +
                ", total_count=" + total_count +
                '}';
    }
}
