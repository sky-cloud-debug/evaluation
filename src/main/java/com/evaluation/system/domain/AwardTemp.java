package com.evaluation.system.domain;

import lombok.*;


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
@NoArgsConstructor
@Setter
@Getter
@ToString
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

    // 获奖等级
    private String level;

    // 奖项名称，复合主键
    @Id
    @Column(name = "awardName")
    private String awardName;

    private String awardtime;

    // 加分数
    private int score;

    // 判断进行到的状态，0未审批，1班长审批通过，2管理员审批通过，-1班长拒绝，-2管理员拒绝
    private int flag;

    // 驳回理由
    private String reason;

    // 班级
    private String classMajor;

    private String route;

    public AwardTemp(String number, String name, String type, String level, String awardName, String awardtime, int score, int flag, String reason, String classMajor, String route) {
        this.number = number;
        this.name = name;
        this.type = type;
        this.level = level;
        this.awardName = awardName;
        this.awardtime = awardtime;
        this.score = score;
        this.flag = flag;
        this.reason = reason;
        this.classMajor = classMajor;
        this.route = route;
    }
}
