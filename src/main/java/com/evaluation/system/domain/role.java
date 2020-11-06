package com.evaluation.system.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class role {
    @Id
    private int id;
    private String name;
}
