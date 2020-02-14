package com.evaluation.system.Conterllor;


import com.evaluation.system.Service.Impl.ScoreServiceImpl;
import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.testGroup;
import com.evaluation.system.util.ExcelUtils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class ScoreController {

    @Autowired
    ScoreServiceImpl scoreService;

    // 展示前台
    @RequestMapping("class")
    public String ClassInfo(HttpServletRequest request, Model model) {
        String classMajor = "计算机18-4"; // 后期Shiro框架传入
        ArrayList<basic> basicArrayList = scoreService.getClassInfo(classMajor);
        model.addAttribute("basiclist", basicArrayList);
        return "scoring/studentlist";
    }

    // 注入班级成员
    @GetMapping("/setClassMember")
    public String setClassMember() throws IOException {
        String classMajor = "计算机18-4";
        ArrayList<basic> basicList = new ArrayList<basic>();
        basicList = scoreService.getClassInfo(classMajor); // 还需要查询时候根据学号排序
        ArrayList<String> nameList = new ArrayList<String>();
        for (int i = 0; i < basicList.size(); ++i) {
            nameList.add(basicList.get(i).getName());
        }
        scoreService.setClassMember(classMajor, nameList);
        return "index";
    }

    // 注入测评小组成员
    @RequestMapping("/test1")
    public String setTestMember() {
        String classMajor = "计算机18-4";
        String type = "Moral";
        ArrayList<testGroup> testGroupList = new ArrayList<testGroup>();
        testGroupList = scoreService.getTestInfo(classMajor);
        ArrayList<String> nameList = new ArrayList<String>();
        for (int i = 0; i < testGroupList.size(); ++i) {
            nameList.add(testGroupList.get(i).getName());
        }
        scoreService.setTestMember(classMajor, nameList);
        return "index";
    }

    // 测评小组打分
    @RequestMapping("/test2")
    public String submitMoralScore() {
        String classMajor = "计算机18-4"; // 班级
        String name = "江宇轩"; // 测评小组打分人名
        String type = "Moral"; // 德育成绩
        ArrayList<Integer> scoreList = new ArrayList<Integer>(); // 打的分数，务必与学号对应，从小到大！！
        // 测试数据-start
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        // -end

        return "";
    }


}
