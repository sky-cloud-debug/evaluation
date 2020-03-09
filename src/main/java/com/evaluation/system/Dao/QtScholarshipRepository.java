package com.evaluation.system.Dao;

import com.evaluation.system.domain.qtScholarship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QtScholarshipRepository extends JpaRepository<qtScholarship,String> {

    //public qtScholarship findByNumberAndBonus_name(String number,String Bonus_name);

}
