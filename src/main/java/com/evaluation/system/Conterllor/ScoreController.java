package com.evaluation.system.Conterllor;


import com.evaluation.system.Service.Impl.ExcelServiceImpl;
import com.evaluation.system.Service.Impl.ScoreServiceImpl;
import com.evaluation.system.Service.ScoreService;
import com.evaluation.system.domain.basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ScoreController {

    @Autowired
    ScoreServiceImpl scoreService;

    @Autowired
    ExcelServiceImpl excelService;

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
            前端传入 前者Integer是number，后者是分数
            通过Shiro传入班级ClassMajor、打分者Name
            共30组映射，上传的是道德成绩
            下面的是测试数据，正式使用可删除
         */
        String classMajor = "计算机18-4";
        String name = "江宇轩";
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(2018212602, 95);
        map.put(2018212603, 65);
        map.put(2018212604, 85);
        // 测试数据输入完毕
        String type = "Moral";
        excelService.submitScore(classMajor, name, type, map);

        return "";
    }


}
