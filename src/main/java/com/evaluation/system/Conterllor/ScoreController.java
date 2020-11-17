package com.evaluation.system.Conterllor;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.evaluation.system.Service.Impl.ScoreServiceImpl;
import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.testGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * author:JYuXuAN
 */
@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    ScoreServiceImpl scoreService;


    /**
     * 展示前台
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/class")
    @ResponseBody
    public ArrayList<basic> ClassInfo(HttpSession session,HttpServletRequest request, Model model) {
        String classMajor = (String) session.getAttribute("classMajor"); // 后期Shiro框架传入
        ArrayList<basic> basicArrayList = scoreService.getClassInfo(classMajor);
//        model.addAttribute("basiclist", basicArrayList);
////        return "scoring/studentlist";
        return basicArrayList;
    }

    /**
     * 获得班级成员所有信息
     *
     * @return
     */
    @RequestMapping("/getAllClassInfo")
    @ResponseBody
    public LinkedHashMap<basic, ArrayList<Double>> getAllInfo(HttpSession session) {
        String classMajor = (String) session.getAttribute("classMajor");
        String name = (String) session.getAttribute("name");
        LinkedHashMap<basic, ArrayList<Double>> map = new LinkedHashMap<>();
        ArrayList<basic> basicArrayList = scoreService.getClassInfo(classMajor);
        ArrayList<ArrayList<Double>> scoreLists = scoreService.getClassScore(classMajor, name);
        for (int i = 0; i < scoreLists.get(0).size(); ++i) {
            ArrayList<Double> scoreList = new ArrayList<>();
            scoreList.add(scoreLists.get(0).get(i));
            scoreList.add(scoreLists.get(1).get(i));
            scoreList.add(scoreLists.get(2).get(i));
            map.put(basicArrayList.get(i), scoreList);
        }
        return map;
    }

    /**
     * 展示班级成员得分情况
     *
     * @param request
     * @return
     */
    @RequestMapping("/getClassScore")
    @ResponseBody
    public ArrayList<ArrayList<Double>> getClassScore(HttpSession session,HttpServletRequest request) {
        String classMajor = (String) session.getAttribute("classMajor");
        String name = (String) session.getAttribute("name");
        ArrayList<ArrayList<Double>> scoreLists = scoreService.getClassScore(classMajor, name);
        return scoreLists;
    }

    /**
     * 注入班级成员
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/setClassMember")
    public String setClassMember(HttpSession session) throws IOException {
        String classMajor = (String) session.getAttribute("classMajor");
        ArrayList<basic> basicList = new ArrayList<basic>();
        basicList = scoreService.getClassInfo(classMajor); // 还需要查询时候根据学号排序
        ArrayList<String> nameList = new ArrayList<String>();
        for (int i = 0; i < basicList.size(); ++i) {
            nameList.add(basicList.get(i).getName());
        }
        scoreService.setClassMember(classMajor, nameList);
        return "index";
    }

    /**
     * 注入测评小组成员
     *
     * @return
     */
    @GetMapping("/setTeamMember")
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

    @RequestMapping("/submitScore")
    public String submitScore(HttpSession session) {
        String classMajor = (String) session.getAttribute("classMajor");
        String name = (String) session.getAttribute("name");
        ArrayList<Integer> moralList = new ArrayList<Integer>(); // 打的分数，务必与学号对应，从小到大！！
        ArrayList<Integer> heartList = new ArrayList<Integer>(); // 打的分数，务必与学号对应，从小到大！！
        ArrayList<Integer> technologyList = new ArrayList<Integer>(); // 打的分数，务必与学号对应，从小到大！！
        scoreService.submitScore(classMajor, name, "Moral", moralList);
        scoreService.submitScore(classMajor, name, "Heart", heartList);
        scoreService.submitScore(classMajor, name, "Technology", technologyList);
        return "";
    }

    @RequestMapping("/submittest")
    public String test(@RequestBody Map<String, Map<String, String>> res,HttpSession session) {
        String classMajor = (String) session.getAttribute("classMajor");
        String name = (String) session.getAttribute("name");
        ArrayList<Integer> moralList = new ArrayList<Integer>(); // 打的分数，务必与学号对应，从小到大！！
        ArrayList<Integer> heartList = new ArrayList<Integer>(); // 打的分数，务必与学号对应，从小到大！！
        ArrayList<Integer> technologyList = new ArrayList<Integer>(); // 打的分数，务必与学号对应，从小到大！！
        for (Map.Entry<String, Map<String, String>> enTry : res.entrySet()) {
            Map<String, String> map = enTry.getValue();
            moralList.add(Integer.parseInt(map.get("moral")));
            heartList.add(Integer.parseInt(map.get("health")));
            technologyList.add(Integer.parseInt(map.get("technology")));
        }
        scoreService.submitScore(classMajor, name, "Moral", moralList);
        scoreService.submitScore(classMajor, name, "Heart", heartList);
        scoreService.submitScore(classMajor, name, "Technology", technologyList);
        return "";
    }

    /**
     * 测评小组打分 德育成绩
     *
     * @return
     */
    @RequestMapping("/submitMoral")
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
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        // -end
        scoreService.submitScore(classMajor, name, type, scoreList);
        return "index";
    }

    /**
     * 测评小组打分 身心成绩
     *
     * @return
     */
    @GetMapping("/submitHeart")
    public String submitHeartScore() {
        String classMajor = "计算机18-4"; // 班级
        String name = "江宇轩"; // 测评小组打分人名
        String type = "Heart"; // 德育成绩
        ArrayList<Integer> scoreList = new ArrayList<Integer>(); // 打的分数，务必与学号对应，从小到大！！
        // 测试数据-start
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        // -end
        scoreService.submitScore(classMajor, name, type, scoreList);
        return "index";
    }

    /**
     * 测评小组打分 科技人文成绩
     *
     * @return
     */
    @GetMapping("/submitTechnology")
    public String submitTechnologyScore() {
        String classMajor = "计算机18-4"; // 班级
        String name = "江宇轩"; // 测评小组打分人名
        String type = "Technology"; // 德育成绩
        ArrayList<Integer> scoreList = new ArrayList<Integer>(); // 打的分数，务必与学号对应，从小到大！！
        // 测试数据-start
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        scoreList.add(65);
        scoreList.add(95);
        scoreList.add(85);
        scoreList.add(75);
        scoreList.add(95);
        // -end
        scoreService.submitScore(classMajor, name, type, scoreList);
        return "index";
    }

    /**
     * 计算学生成绩
     *
     * @return
     */
    @GetMapping("/Calculation")
    public String Calculation(HttpSession session) {
        String classMajor = (String) session.getAttribute("classMajor");
        scoreService.Calculate(classMajor);
        return "index";
    }


}
