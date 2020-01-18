package com.evaluation.system.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@ToString
@Data
public class quality {

    //学号
    private Integer number;

    //道德素养
    private Double moral;

    //智育成绩
    private Double wisdom;

    //身心素养
    private Double heart;

    //科技人文
    private Double technology;

    //总分
    private Double totalCount;

    //专业班级
    private String classMajor;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public Double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Double totalCount) {
        this.totalCount = totalCount;
    }

    public String getClassMajor() {
        return classMajor;
    }

    public void setClassMajor(String classMajor) {
        this.classMajor = classMajor;
    }

    @Override
    public String toString() {
        return "quality{" +
                "number=" + number +
                ", moral=" + moral +
                ", wisdom=" + wisdom +
                ", heart=" + heart +
                ", technology=" + technology +
                ", totalCount=" + totalCount +
                ", classMajor='" + classMajor + '\'' +
                '}';
    }
}
