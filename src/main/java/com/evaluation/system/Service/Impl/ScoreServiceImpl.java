package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Service.ScoreService;
import com.evaluation.system.domain.basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    BasicRepository basicRepository;

    @Override
    public ArrayList<basic> getClassInfo(String classMajor) {
        ArrayList<basic>list=basicRepository.findByClassMajor(classMajor);
        return list;
    }



}
