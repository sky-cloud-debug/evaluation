package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.Impl.ExamineServiceImpl;
import com.evaluation.system.domain.AwardTemp;
import com.evaluation.system.util.DateUtils;
import com.evaluation.system.util.RouterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author:江宇轩
 */
@Controller
@RequestMapping("/examine")
public class ExamineController {

    @Autowired
    ExamineServiceImpl examineService;

    @GetMapping("/test")
    public int test(HttpSession session) {
        String number = (String) session.getAttribute("number"); // 获取当前登录用户
        String classMajor = (String) session.getAttribute("classMajor");
        String name = (String) session.getAttribute("name");
        System.out.println(number + " " + classMajor + " " + name);
        return 0;
    }


    /**
     * 提交加分项材料，保存照片，保存数据库
     *
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/subMaterials")
    @ResponseBody
    public String subMaterials(@RequestParam(value = "materials") MultipartFile file, HttpServletRequest request, HttpSession session) {
        // Shiro传入开始
        String number = (String) session.getAttribute("number"); // 获取当前登录用户
        String classMajor = (String) session.getAttribute("classMajor");
        String name = (String) session.getAttribute("name");
        // Shiro传入结束
        String type = request.getParameter("type");
        String level = request.getParameter("level");
        String awardName = request.getParameter("awardName");
        String sc = request.getParameter("score");

        Date updateTime = DateUtils.getCurrentDate();
        String uuid = RouterUtils.generateRandomString();
        String router = number + '-' + uuid;

        int score = Integer.parseInt(sc);
        AwardTemp awardTemp = new AwardTemp(number, name, type, level, awardName, score, 0, "", classMajor, router, updateTime);
        String info = examineService.insertAwardTemp(awardTemp, file);
        return info;
    }

    /**
     * 管理员：展示所有已通过的材料
     *
     * @param request
     * @return
     */
    @GetMapping("/showApprovedMaterials_admin")
    @ResponseBody
    public ArrayList<AwardTemp> showApprovedMaterials_admin(HttpServletRequest request, HttpSession session) {
        String classMajor = (String) session.getAttribute("classMajor");
        ArrayList<AwardTemp> awardTemps = new ArrayList<AwardTemp>();
        awardTemps = examineService.getAwardTempInfo(classMajor, 2);
        return awardTemps;
    }

    /**
     * 管理员：审核材料->展示前台
     *
     * @param request
     * @return
     */
    @GetMapping("/showMaterials_admin")
    @ResponseBody
    public ArrayList<AwardTemp> showMaterials_admin(@RequestBody(required=false)String major,HttpServletRequest request, HttpSession session) {
        //TODO 自己选择班级
        String classMajor=request.getParameter("major");
        System.out.println(classMajor);
        //String classMajor = (String) session.getAttribute("classMajor");
        ArrayList<AwardTemp> awardTemps = new ArrayList<AwardTemp>();
        awardTemps = examineService.getAwardTempInfo(classMajor, 1);
        return awardTemps;
    }

    /**
     * 班长：审核材料->展示前台
     *
     * @param request
     * @return
     */
    @GetMapping("/showMaterials_monitor")
    @ResponseBody
    public ArrayList<AwardTemp> showMaterials_monitor(HttpServletRequest request, HttpSession session) {
        String classMajor = (String) session.getAttribute("classMajor");
        ArrayList<AwardTemp> awardTemps = new ArrayList<AwardTemp>();
        awardTemps = examineService.getAwardTempInfo(classMajor, 0);
        return awardTemps;
    }

    /**
     * 管理员审核
     *
     * @return
     */
    @GetMapping("/judgeMaterials_admin")
    @ResponseBody
    public String judgeMaterials_admin(HttpServletRequest request) {
        String number = request.getParameter("number");
        String awardName = request.getParameter("awardName");
        String FLAG = request.getParameter("flag");
        String reason = request.getParameter("reason");
        int flag = 0;
        if (FLAG.equals("true")) {
            flag = 2;
        } else {
            flag = -2;
        }
        String result = examineService.judgeMaterials_admin(number, awardName, flag, reason);
        System.out.println(result);
        return result;
    }

    /**
     * 班长审核
     *
     * @return
     */
    @GetMapping("/judgeMaterials_monitor")
    @ResponseBody
    public String judgeMaterials_monitor(HttpServletRequest request) {
        String number = request.getParameter("number");
        String awardName = request.getParameter("awardName");
        String FLAG = request.getParameter("flag");
        String reason = request.getParameter("reason");
        int flag = 0;
        if (FLAG.equals("true")) {
            flag = 1;
        } else {
            flag = -1;
        }
        String result = examineService.judgeMaterials_monitor(number, awardName, flag, reason);
        System.out.println(result);
        return result;
    }


    /**
     * 学生端显示是否通过
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/judgeResult")
    public String judgeResult(HttpServletRequest request, Model model, HttpSession session) {
        String number = (String) session.getAttribute("number"); // 获取当前登录用户
        ArrayList<AwardTemp> awardTemps = new ArrayList<AwardTemp>();
        awardTemps = examineService.getJudgeResult(number);
        model.addAttribute("awardTemp", awardTemps);
        return "scoring/materials";
    }

    /**
     * 通过条件查找所有未审批的awardTemp
     *
     * @param request
     * @return
     */
    @GetMapping("/findAwardTemp")
    @ResponseBody
    public ArrayList<AwardTemp> showAwardTemp(HttpServletRequest request) {
        ArrayList<AwardTemp> awardTemps = new ArrayList<>();
        String classMajor = null; // 班级查询
        String type = null; // 类型查询
        String level = null; // 等级查询
        String flag = null;
        try {
            classMajor = request.getParameter("classMajor");
            type = request.getParameter("type");
            level = request.getParameter("level");
            flag = request.getParameter("flag");
            if (flag == null) {
                awardTemps = examineService.findAwardTemp(classMajor, type, level);
            } else {
                int fg = Integer.parseInt(flag);
                awardTemps = examineService.findAwardTemp(classMajor, type, level, fg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return awardTemps;
    }

    /**
     * 管理员审核中的下拉框，获得所有班级
     *
     * @return
     */
    @GetMapping("/getAllClassMajor")
    @ResponseBody
    public List<String> showAllClassMajor() {
        List<String> classMajors;
        classMajors = examineService.getAllClassMajor();
        return classMajors;
    }


}
