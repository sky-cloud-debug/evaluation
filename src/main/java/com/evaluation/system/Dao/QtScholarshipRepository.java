package com.evaluation.system.Dao;

import com.evaluation.system.domain.qtScholarship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QtScholarshipRepository extends JpaRepository<qtScholarship,String> {

    @Query(value = "select new com.evaluation.system.domain.qtScholarship(a.number,a.bonus_name,a.card_number,a.state,a.reason) from qtScholarship a where a.number=?1 and a.bonus_name=?2")
    public qtScholarship findByNumberAndBonus_name(String number,String Bonus_name);

    @Modifying
    @Query(value = "delete from qtScholarship a where a.number=?1 and a.bonus_name=?2")
    public int deleteByNumberAndBonus_name(String number,String Bonus_name);

    @Query(value = "select new com.evaluation.system.domain.qtScholarship(a.number,a.bonus_name,a.card_number,a.state,a.reason) from qtScholarship a where a.number=?1")
    public List<qtScholarship> findByNumber(String number);
}
