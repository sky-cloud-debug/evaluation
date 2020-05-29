package com.evaluation.system.Dao;

import com.evaluation.system.domain.AwardTemp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

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

    public AwardTemp findByNameAndAwardName(String name, String awardName);

}
