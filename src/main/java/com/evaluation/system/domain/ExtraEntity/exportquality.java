package com.evaluation.system.domain.ExtraEntity;

public class exportquality {

    private String number;

    private String name;

    //专业班级
    private String classMajor;
    //排名
    private int rank;

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

    public exportquality() {
    }

    public exportquality(String number, String name, String classMajor, int rank, Double moral, Double wisdom, Double heart, Double technology, Double totalCount) {
        this.number = number;
        this.name = name;
        this.classMajor = classMajor;
        this.rank = rank;
        this.moral = moral;
        this.wisdom = wisdom;
        this.heart = heart;
        this.technology = technology;
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "exportquality{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", classMajor='" + classMajor + '\'' +
                ", rank=" + rank +
                ", moral=" + moral +
                ", wisdom=" + wisdom +
                ", heart=" + heart +
                ", technology=" + technology +
                ", totalCount=" + totalCount +
                '}';
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

    public String getClassMajor() {
        return classMajor;
    }

    public void setClassMajor(String classMajor) {
        this.classMajor = classMajor;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
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
}
