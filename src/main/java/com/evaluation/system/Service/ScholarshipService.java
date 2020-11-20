package com.evaluation.system.Service;

import com.evaluation.system.domain.ExtraEntity.AllqtAwards;
import com.evaluation.system.domain.ExtraEntity.AllxyAwards;
import com.evaluation.system.domain.ExtraEntity.VerifyQtScholarship;
import com.evaluation.system.domain.ExtraEntity.VerifyYxScholarship;
import com.evaluation.system.domain.qtScholarship;
import com.evaluation.system.domain.yxScholarship;

import java.util.List;

public interface ScholarshipService {
    public boolean add_yx(yxScholarship yxScholarship);

    public boolean add_qt(qtScholarship qtScholarship);

    public boolean updata_qt(qtScholarship qt);

    public boolean updata_yx(yxScholarship yxScholarship);

    public boolean delete_qt(String number,String Bonus_name,String year);

    public boolean delete_xy(String numbre,int level,String year);

    public int updateQtScholarshipState(int state,String number,String year,String bonusname,String reason);
    public int updateYxScholarshipState(int state,String number,String year,String reason);
    public List<VerifyYxScholarship> verifyYxscholarshipByClass(String classmajor, int state);
    public List<VerifyQtScholarship> verifyQtscholarshipByClass(String classmajor, int state);
    //查询个人所有的奖项
    public List<qtScholarship> findqtByNumber(String number);
    public List<yxScholarship> findyxByNumber(String number);
    //班级展示所有的奖项
    public List<AllqtAwards> findqtByYearAndClassMajor(String year, String classmajor);
    public List<AllxyAwards> findyxByYearAndClassMajor(String year, String classmajor);
    //查询单个奖项
    public qtScholarship findByNumberAndBonus_name(String number,String Bonus_name);

}
