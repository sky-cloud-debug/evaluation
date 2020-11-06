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
public class roleUser {
    @Id
    private int id;
    private int userid;
    private int roleid;
}
