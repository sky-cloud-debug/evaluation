package com.evaluation.system.domain;

import java.io.Serializable;
import java.util.Objects;

public class AwardPK implements Serializable {

    private String number;

    private String awardName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardPK awardPK = (AwardPK) o;
        return number.equals(awardPK.number) &&
                awardName.equals(awardPK.awardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, awardName);
    }

    public AwardPK() {
    }

    public AwardPK(String number, String awardName) {
        this.number = number;
        this.awardName = awardName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }
}
