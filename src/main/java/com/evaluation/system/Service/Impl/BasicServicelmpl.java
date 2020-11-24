package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Service.BasicService;
import com.evaluation.system.domain.ExtraEntity.AllqtAwards;
import com.evaluation.system.domain.ExtraEntity.AllxyAwards;
import com.evaluation.system.domain.ExtraEntity.ShowStu;
import com.evaluation.system.domain.basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class BasicServicelmpl implements BasicService {

    @Autowired
    BasicRepository basicRepository;


    @Override
    public basic findByNumber(String number) {
        return basicRepository.findByNumber(number);
    }

    @Override
    public String findClass() {
        return basicRepository.findClass();
    }

    @Override
    public boolean updatebasic(basic b) {
        basic save = basicRepository.save(b);
        return save!=null;
    }

    @Override
    public ArrayList<basic> findByClassMajorOrderByNumber(String classMajor) {
        return basicRepository.findByClassMajorOrderByNumber(classMajor);
    }

    @Override
    public boolean addbasic(basic b) {
        basic save = basicRepository.save(b);
        return save!=null;
    }

    @Override
    public String findDutyByNumber(String number) {
        return basicRepository.findDutyByNumber(number);
    }

    @Override
    public List<ShowStu> ShowScore(String classmajor) {
        return basicRepository.ShowScore(classmajor);
    }

    @Override
    public List<ShowStu> ShowScoreLike(String classmajor) {
        return basicRepository.ShowScoreLike(classmajor);
    }

    @Override
    public List<AllqtAwards> FindqtAward() {
        return basicRepository.FindqtAward();
    }

    @Override
    public List<AllxyAwards> FindyxAward() {
        return basicRepository.FindyxAward();
    }
}
