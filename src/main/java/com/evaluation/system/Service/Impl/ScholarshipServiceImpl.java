package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.QtScholarshipRepository;
import com.evaluation.system.Dao.yxScholarshipRepository;
import com.evaluation.system.Service.ScholarshipService;
import com.evaluation.system.domain.ExtraEntity.AllqtAwards;
import com.evaluation.system.domain.ExtraEntity.AllxyAwards;
import com.evaluation.system.domain.qtScholarship;
import com.evaluation.system.domain.yxScholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ScholarshipServiceImpl implements ScholarshipService {

    @Autowired
    yxScholarshipRepository yxScholarshipRepository;

    @Autowired
    QtScholarshipRepository qtScholarshipRepository;
    //添加奖项
    @Override
    public boolean add_yx(yxScholarship yxScholarship) {
        yxScholarship save = yxScholarshipRepository.save(yxScholarship);
        return save!=null;
    }

    @Override
    public boolean add_qt(qtScholarship qtScholarship) {
        qtScholarship save=qtScholarshipRepository.save(qtScholarship);
        return save!=null;
    }
    //更新奖项
    @Override
    public boolean updata_qt(qtScholarship qt) {

        qtScholarship save = qtScholarshipRepository.save(qt);
        return save!=null;
    }

    @Override
    public boolean updata_yx(yxScholarship yxScholarship) {
        yxScholarship save = yxScholarshipRepository.save(yxScholarship);
        return save!=null;
    }
    //删除奖项
    @Override
    public boolean delete_qt(String number, String Bonus_name,String year) {
        int i = qtScholarshipRepository.deleteByNumberAndBonus_nameAndYear(number, Bonus_name,year);
        if(i==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean delete_xy(String numbre, int level, String year) {
        int i = yxScholarshipRepository.deleteByNumberAndScholarshipLevelAndYear(numbre, level, year);
        if(i==1){
            return true;
        }
        return false;
    }

    //查询个人
    @Override
    public List<qtScholarship> findqtByNumber(String number) {
        return qtScholarshipRepository.findByNumber(number);
    }

    @Override
    public List<yxScholarship> findyxByNumber(String number) {
        return yxScholarshipRepository.findByNumber(number);
    }

    @Override
    public List<AllqtAwards> findqtByYearAndClassMajor(String year, String classmajor) {
        return qtScholarshipRepository.findByYearAndClassMajor(year,classmajor);
    }

    @Override
    public List<AllxyAwards> findyxByYearAndClassMajor(String year, String classmajor) {
        return yxScholarshipRepository.findByYearAndClassMajor(year,classmajor);
    }
    //查询个人奖项

    @Override
    public qtScholarship findByNumberAndBonus_name(String number, String Bonus_name) {
        return qtScholarshipRepository.findByNumberAndBonus_name(number,Bonus_name);
    }


}
