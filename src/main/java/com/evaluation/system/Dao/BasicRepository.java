package com.evaluation.system.Dao;

import com.evaluation.system.domain.ExtraEntity.AllqtAwards;
import com.evaluation.system.domain.ExtraEntity.AllxyAwards;
import com.evaluation.system.domain.ExtraEntity.ShowStu;
import com.evaluation.system.domain.basic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface BasicRepository extends JpaRepository<basic, String> {

    public basic findByNumber(String number);

    @Query(value = "select class_major from basic limit 1",nativeQuery = true)
    public String findClass();

    public ArrayList<basic> findByClassMajorOrderByNumber(String classMajor);

    @Query(value = "select duty from basic where number=?1")
    public String findDutyByNumber(String number);

    //这是从quality与basic两个表中查询的数据，实体类是ShowStu
    @Query(value = "select new com.evaluation.system.domain.ExtraEntity.ShowStu(a.number,a.name,a.sex,a.classMajor,b.moral,b.wisdom,b.heart,b.technology,b.totalCount) from basic a,quality b where a.number = b.number and a.classMajor=?1 order by a.number")
    public List<ShowStu> ShowScore(String classmajor);

    //这是从quality与basic两个表中查询的数据，实体类是ShowStu,模糊查询
    @Query(value = "select new com.evaluation.system.domain.ExtraEntity.ShowStu(a.number,a.name,a.sex,a.classMajor,b.moral,b.wisdom,b.heart,b.technology,b.totalCount) from basic a,quality b where a.number = b.number and a.classMajor like ?1 order by a.number")
    public List<ShowStu> ShowScoreLike(String classmajor);

    //这是从basic与qtscholarship查找的除奖学金的奖项
    @Query(value = "select new com.evaluation.system.domain.ExtraEntity.AllqtAwards(a.number,a.name,b.bonus_name) from basic a,qtScholarship b  where a.number = b.number order by a.number")
    public List<AllqtAwards> FindqtAward();

    //这里是查询的奖学金等级
    @Query(value = "select new com.evaluation.system.domain.ExtraEntity.AllxyAwards(a.number,a.name,b.scholarshipLevel) from basic a,yxScholarship b  where a.number = b.number order by a.number")
    public List<AllxyAwards> FindyxAward();
}
