package com.evaluation.system.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@ToString

public class ExtraItem {
    @Id
    @Column
    private int imageId;

    private String type1;
    private String type2;
    private String extraItemName;
    private String level;
    private String file;

    public ExtraItem(int imageId, String type1, String type2, String extraItemName, String level, String file) {
        this.type1 = type1;
        this.type2 = type2;
        this.extraItemName = extraItemName;
        this.level = level;
        this.file = file;
    }

    public int getImageId(){
        return imageId;
    }

    public void setImageId(int imageId){
        this.imageId = imageId;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getExtraItemName() {
        return extraItemName;
    }

    public void setExtraItemName(String extraItemName) {
        this.extraItemName = extraItemName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
