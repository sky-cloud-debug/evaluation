package com.evaluation.system.domain;

import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * author:江宇轩
 */
@Entity
@Data
@IdClass(AwardPK.class)
public class Award implements Serializable {

    // 学号，主键
    @Id
    @Column(name = "number")
    private String number;

    // 姓名
    private String name;

    // 获奖类型
    private String type;

    // 获奖等级
    private String level;

    // 奖项名称，复合主键
    @Id
    @Column(name = "awardName")
    private String awardName;

    // 加分数
    private int score;

    // 班级
    private String classMajor;

    public Award() {
    }

    public Award(String number, String name, String type, String level, String awardName, int score, String classMajor) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.level = level;
        this.awardName = awardName;
        this.score = score;
        this.classMajor = classMajor;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getClassMajor() {
        return classMajor;
    }

    public void setClassMajor(String classMajor) {
        this.classMajor = classMajor;
    }

    @Override
    public String toString() {
        return "Award{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", level='" + level + '\'' +
                ", awardName='" + awardName + '\'' +
                ", score=" + score +
                ", classMajor='" + classMajor + '\'' +
                '}';
    }
}
