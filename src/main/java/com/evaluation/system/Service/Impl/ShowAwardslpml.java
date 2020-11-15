package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.QtScholarshipRepository;
import com.evaluation.system.Dao.yxScholarshipRepository;
import com.evaluation.system.Service.ShowAwards;
import com.evaluation.system.domain.*;
import com.evaluation.system.domain.ExtraEntity.AllqtAwards;
import com.evaluation.system.domain.ExtraEntity.AllxyAwards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class ShowAwardslpml implements ShowAwards {

    @Autowired
    BasicRepository basicRepository;

    @Autowired
    QtScholarshipRepository qtScholarshipRepository;

    @Autowired
    yxScholarshipRepository yxScholarshipRepository;

    @Override
    public basic ShowStudent(String num) {
        return basicRepository.getOne(num);
    }

    @Override
    public List<AllqtAwards> findAllqtAwards() {
        List<AllqtAwards> list=new ArrayList<AllqtAwards>();
        list=basicRepository.FindqtAward();
        if(list==null){
            return null;
        }else {
            return list;
        }
    }

    @Override
    public List<AllxyAwards> findAllyxAwards() {
        List<AllxyAwards> list=new ArrayList<AllxyAwards>();
        list=basicRepository.FindyxAward();
        if(list==null){
            return null;
        }else {
            return list;
        }
    }

    @Override
    public List<qtScholarship> findPersonAllByNumber(String number) {
        return qtScholarshipRepository.findByNumber(number);
    }

    @Override
    public List<yxScholarship> findyxByNumber(String number) {
        return yxScholarshipRepository.findByNumber(number);
    }

    @Override
    public List<AllqtAwards> findAllqtAwardsByClass(String classma) {
        return null;
    }


    @Override
    public List<AllxyAwards> findAllyxAwardsByClass(String classmajor) {
        return null;
    }
}
