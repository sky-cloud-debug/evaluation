package com.evaluation.system.Dao;

import com.evaluation.system.domain.AwardTemp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface AwardTempRepository extends JpaRepository<AwardTemp, String> {

    public ArrayList<AwardTemp> findByClassMajorAndJudge(String classMajor, String judge);

    public ArrayList<AwardTemp> findByJudge(String judge);

    public ArrayList<AwardTemp> findByNumber(String number);

    public AwardTemp findByNameAndAwardName(String name, String awardName);

}
