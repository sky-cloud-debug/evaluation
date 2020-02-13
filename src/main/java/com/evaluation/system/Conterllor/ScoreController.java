package com.evaluation.system.Conterllor;


import com.evaluation.system.Service.Impl.ScoreServiceImpl;
import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.testGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("submitMoral")
    public String submitMoral(HttpServletRequest request, Model model) {
        /*
            前端传入list列表，存放道德成绩
            前端展示的名字，与excel表格中列的对应名字顺序不能出现任何错误！！！
            下面的是测试数据，正式使用可删除
         */
        String classMajor = "计算机18-4";
        String name = "江宇轩";
        String type = "Moral";
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(95);
        list.add(85);
        list.add(75);
        list.add(65);
        list.add(85);
        scoreService.submitMoralScore(classMajor, name, type, list);
        return "";
    }

    @GetMapping("/setClassMember")
    public String setClassMember() {
        String classMajor = "计算机18-4";
        String type = "Moral";
        ArrayList<basic> basicList = new ArrayList<basic>();
        basicList = scoreService.getClassInfo(classMajor); // 还需要查询时候根据学号排序
        ArrayList<String> nameList = new ArrayList<String>();
        for (int i = 0; i < basicList.size(); ++i) {
            nameList.add(basicList.get(i).getName());
        }
        scoreService.setClassMember(classMajor, type, nameList);
        return "index";
    }

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
