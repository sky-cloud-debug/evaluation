package com.evaluation.system.domain;

import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;

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

    // 图片路径
    private String router;

    // 上传时间
    @Column(name = "update_time")
    private Date updateTime;

    public Award() {
    }

    public Award(String number, String name, String type, String level, String awardName, int score, String classMajor, String router, Date updateTime) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.level = level;
        this.awardName = awardName;
        this.score = score;
        this.classMajor = classMajor;
        this.router = router;
        this.updateTime = updateTime;
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

    public String getRouter() {
        return router;
    }

    public void setRouter(String router) {
        this.router = router;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
                ", router='" + router + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
