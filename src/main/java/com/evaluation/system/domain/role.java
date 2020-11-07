package com.evaluation.system.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor

public class role {
    @Id
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
