package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.QtScholarshipRepository;
import com.evaluation.system.Dao.yxScholarshipRepository;
import com.evaluation.system.Service.ShowAwards;
import com.evaluation.system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowAwardslpml implements ShowAwards {

    @Autowired
    BasicRepository basicRepository;

    @Autowired
    QtScholarshipRepository qtScholarshipRepository;

    @Autowired
    yxScholarshipRepository yxScholarshipRepository;

    @Override
    public yxScholarship Showyxawards(String num) {
        return yxScholarshipRepository.getOne(num);
    }

    @Override
    public qtScholarship Showqtawards(String num) {
        return qtScholarshipRepository.getOne(num);
    }

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
}
