package com.evaluation.system.Service;

import com.evaluation.system.domain.ExtraEntity.AllqtAwards;
import com.evaluation.system.domain.ExtraEntity.AllxyAwards;
import com.evaluation.system.domain.ExtraEntity.ShowStu;
import com.evaluation.system.domain.basic;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface BasicService {

    public basic findByNumber(String number);

    public boolean updatebasic(basic b);
    public ArrayList<basic> findByClassMajorOrderByNumber(String classMajor);
    public boolean addbasic(basic b);
    public String findDutyByNumber(String number);

    //这是从quality与basic两个表中查询的数据，实体类是ShowStu
    public List<ShowStu> ShowScore(String classmajor);

    //这是从quality与basic两个表中查询的数据，实体类是ShowStu,模糊查询
    public List<ShowStu> ShowScoreLike(String classmajor);

    //这是从basic与qtscholarship查找的除奖学金的奖项
    public List<AllqtAwards> FindqtAward();

    //这里是查询的奖学金等级
    public List<AllxyAwards> FindyxAward();

}
