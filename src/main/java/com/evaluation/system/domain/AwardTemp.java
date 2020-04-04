package com.evaluation.system.domain;

import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Data
@IdClass(AwardPK.class)
public class AwardTemp implements Serializable {

    // 学号，主键
    @Id
    @Column(name = "number")
    private String number;

    // 姓名
    private String name;

    // 获奖类型
    private String type;

    // 奖项名称，复合主键
    @Id
    @Column(name="awardName")
    private String awardName;

    // 加分数
    private int score;

    // 审核结果
    private String judge;

    // 驳回理由
    private String reason;

    // 班级
    private String classMajor;

    public AwardTemp() {
    }

    public AwardTemp(String number, String name, String type, String awardName, int score, String classMajor) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.awardName = awardName;
        this.score = score;
        this.classMajor = classMajor;
    }

    public AwardTemp(String number, String name, String type, String awardName, int score, String judge, String reason, String classMajor) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.awardName = awardName;
        this.score = score;
        this.judge = judge;
        this.reason = reason;
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

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getClassMajor() {
        return classMajor;
    }

    public void setClassMajor(String classMajor) {
        this.classMajor = classMajor;
    }

    @Override
    public String toString() {
        return "AwardTemp{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", awardName='" + awardName + '\'' +
                ", score=" + score +
                ", judge='" + judge + '\'' +
                ", reason='" + reason + '\'' +
                ", classMajor='" + classMajor + '\'' +
                '}';
    }
}
