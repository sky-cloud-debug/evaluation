package com.evaluation.system.Dao;

import com.evaluation.system.domain.qtScholarship;
import com.evaluation.system.domain.yxScholarship;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface yxScholarshipRepository extends JpaRepository<yxScholarship,String> {
    //String number, String scholarshipLevel, Integer cardNumber, String state, String reason) {
    @Query(value = "select new com.evaluation.system.domain.yxScholarship(a.number,a.scholarshipLevel,a.cardNumber,a.state,a.reason) from yxScholarship a where a.number=?1")
    public yxScholarship findyxByNumber(String number);

}
