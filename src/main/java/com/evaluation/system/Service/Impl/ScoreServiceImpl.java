package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.QualityRepository;
import com.evaluation.system.Dao.TestGroupRepository;
import com.evaluation.system.Service.ScoreService;
import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.quality;
import com.evaluation.system.domain.testGroup;
import com.evaluation.system.util.ExcelUtils.ExcelReadUtils;
import com.evaluation.system.util.ExcelUtils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

/**
 * author:江宇轩
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    BasicRepository basicRepository;

    @Autowired
    QualityRepository qualityRepository;

    @Autowired
    TestGroupRepository testGroupRepository;

    @Value("${excelPath}")
    public String excelPath;

    ExcelUtils excelUtils = new ExcelUtils();

    /**
     * 得到班级成员信息
     *
     * @param classMajor
     * @return
     */
    @Override
    public ArrayList<basic> getClassInfo(String classMajor) {
        ArrayList<basic> list = basicRepository.findByClassMajorOrderByNumber(classMajor);
        return list;
    }

    /**
     * 获得该同学的打分情况
     *
     * @param classMajor
     * @param name
     * @return
     */
    public ArrayList<ArrayList<Double>> getClassScore(String classMajor, String name) {
        ArrayList<ArrayList<Double>> scoreLists = ExcelReadUtils.getTestMemberScores(excelPath, classMajor, name);
        return scoreLists;
    }

    /**
     * 得到测评小组成员信息
     *
     * @param classMajor
     * @return
     */
    @Override
    public ArrayList<testGroup> getTestInfo(String classMajor) {
        ArrayList<testGroup> list = testGroupRepository.findByClassMajorOrderByNumber(classMajor);
        return list;
    }

    /**
     * 为对应excel表格注入班级成员
     *
     * @param classMajor
     * @param namelist
     * @return
     * @throws IOException
     */
    public String setClassMember(String classMajor, ArrayList<String> namelist) throws IOException {
        excelUtils.setHead(classMajor, "Moral", namelist);
        excelUtils.setHead(classMajor, "Heart", namelist);
        excelUtils.setHead(classMajor, "Technology", namelist);
        return "Success";
    }

    /**
     * 为对应excel表格注入测评小组成员
     *
     * @param classMajor
     * @param scoreList
     * @return
     */
    public String setTestMember(String classMajor, ArrayList<String> scoreList) {
        excelUtils.setTest(classMajor, "Moral", scoreList);
        excelUtils.setTest(classMajor, "Heart", scoreList);
        excelUtils.setTest(classMajor, "Technology", scoreList);
        return "Success";
    }

    /**
     * 注入分数  班级--打分者姓名--打分项--成绩列表
     *
     * @param classMajor
     * @param name
     * @param type
     * @param scoreList
     * @return
     */
    public String submitScore(String classMajor, String name, String type, ArrayList<Integer> scoreList) {
        excelUtils.inputScore(classMajor, name, type, scoreList);
        return "Success";
    }

    /**
     * 班级成员分数计算
     *
     * @param classMajor
     * @return
     */
    public String Calculate(String classMajor) {
        ArrayList<basic> basicList = basicRepository.findByClassMajorOrderByNumber(classMajor);
        ArrayList<quality> qualityList = new ArrayList<quality>();
        ArrayList<Double> moralScoreList = excelUtils.Calculate(classMajor, "Moral");
        ArrayList<Double> heartScoreList = excelUtils.Calculate(classMajor, "Heart");
        ArrayList<Double> technologyScoreList = excelUtils.Calculate(classMajor, "Technology");
        for (int i = 0; i < basicList.size(); ++i) {
            quality q = new quality();
            q.setNumber(basicList.get(i).getNumber());
            q.setMoral(moralScoreList.get(i));
            q.setHeart(heartScoreList.get(i));
            q.setTechnology(technologyScoreList.get(i));
            q.setClassMajor(classMajor);
            qualityList.add(q);
        }
        qualityRepository.saveAll(qualityList);
        return "Success";
    }

}
