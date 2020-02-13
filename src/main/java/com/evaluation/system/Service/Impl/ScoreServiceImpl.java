package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.TestGroupRepository;
import com.evaluation.system.Service.ScoreService;
import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.testGroup;
import com.evaluation.system.util.ExcelUtils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    BasicRepository basicRepository;

    @Autowired
    TestGroupRepository testGroupRepository;

    ExcelUtils excelUtils = new ExcelUtils();

    @Override
    public ArrayList<basic> getClassInfo(String classMajor) {
        ArrayList<basic> list = basicRepository.findByClassMajor(classMajor);
        return list;
    }

    @Override
    public ArrayList<testGroup> getTestInfo(String classMajor) {
        ArrayList<testGroup> list = testGroupRepository.findByClassMajor(classMajor);
        return list;
    }

    // 为对应excel表格注入班级成员
    public String setClassMember(String classMajor, String type, ArrayList<String> namelist) {
        excelUtils.setHead(classMajor, type, namelist);
        return "Success";
    }

    // 为对应excel表格注入测评小组成员
    public String setTestMember(String classMajor, ArrayList<String> scoreList) {
        excelUtils.setTest(classMajor, scoreList);
        return "Success";
    }

    // 注入分数
    public String submitMoralScore(String classMajor, String name, String type, ArrayList<Integer> scoreList) {
        excelUtils.inputScore(classMajor, name, type, scoreList);
        return "Success";
    }


}
