package com.evaluation.system.Dao;

import com.evaluation.system.domain.ExtraEntity.AllqtAwards;
import com.evaluation.system.domain.qtScholarship;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QtScholarshipRepository extends JpaRepository<qtScholarship,String> {

    //用来查询个人所有
    public List<qtScholarship> findByNumber(String number);

    //用来查询班级内所有，按照学年查询
    @Query(value = "select new com.evaluation.system.domain.ExtraEntity.AllqtAwards(a.number,b.name,a.bonus_name) from qtScholarship a,basic b where a.number=b.number and a.year=?1 and b.classMajor=?2")
    public List<AllqtAwards> findByYearAndClassMajor(String year,String classmajor);

    @Query(value = "select new com.evaluation.system.domain.qtScholarship(a.id,a.number,a.bonus_name,a.card_number,a.year,a.state,a.reason) from qtScholarship a where a.number=?1 and a.bonus_name=?2")
    public qtScholarship findByNumberAndBonus_name(String number,String Bonus_name);

    @Modifying
    @Query(value = "delete from qtScholarship a where a.number=?1 and a.bonus_name=?2 and a.year=?3")
    public int deleteByNumberAndBonus_nameAndYear(String number,String Bonus_name,String year);


}
