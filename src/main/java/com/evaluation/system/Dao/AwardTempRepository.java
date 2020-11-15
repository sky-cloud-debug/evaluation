package com.evaluation.system.Dao;

import com.evaluation.system.domain.AwardTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * author:江宇轩
 */
public interface AwardTempRepository extends JpaRepository<AwardTemp, String> {

    public ArrayList<AwardTemp> findByClassMajorAndFlag(String classMajor, int flag);

    public ArrayList<AwardTemp> findByClassMajor(String classMajor);

    public ArrayList<AwardTemp> findByTypeAndFlag(String type, int flag);

    public ArrayList<AwardTemp> findByType(String type);

    public ArrayList<AwardTemp> findByLevelAndFlag(String level, int flag);

    public ArrayList<AwardTemp> findByLevel(String level);

    public ArrayList<AwardTemp> findByNumber(String number);

    public AwardTemp findByNumberAndAwardName(String number, String awardName);

    @Query(value = "select class_major from award_temp group by class_major;", nativeQuery = true)
    List<String> getClassMajorGroupByClassMajor();

}
