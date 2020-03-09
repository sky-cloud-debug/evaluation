package com.evaluation.system.Dao;

import com.evaluation.system.domain.AllqtAwards;
import com.evaluation.system.domain.AllxyAwards;
import com.evaluation.system.domain.ShowStu;
import com.evaluation.system.domain.basic;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface BasicRepository extends JpaRepository<basic, String> {

    public basic findByClassMajor(Integer number);

    public basic findByNumberContains(String number);

    public ArrayList<basic> findByClassMajorOrderByNumber(String classMajor);

    //这是从quality与basic两个表中查询的数据，实体类是ShowStu
    @Query(value = "select new com.evaluation.system.domain.ShowStu(a.number,a.name,a.sex,a.classMajor,b.moral,b.wisdom,b.heart,b.technology,b.totalCount) from basic a,quality b  where a.number = b.number")
    public List<ShowStu> ShowScore();

    //这是从basic与qtscholarship查找的除奖学金的奖项
    @Query(value = "select new com.evaluation.system.domain.AllqtAwards(a.number,a.name,a.classMajor,b.bonus_name) from basic a,qtScholarship b  where a.number = b.number")
    public List<AllqtAwards> FindqtAward();

    //这里是查询的奖学金等级
    @Query(value = "select new com.evaluation.system.domain.AllxyAwards(a.number,a.name,a.classMajor,b.scholarshipLevel) from basic a,yxScholarship b  where a.number = b.number")
    public List<AllxyAwards> FindyxAward();
}

//push在哪来着

//在上边hhh