package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.Impl.ExamineServiceImpl;
import com.evaluation.system.domain.AwardTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * author:江宇轩
 */
@Controller
public class ExamineController {

    @Autowired
    ExamineServiceImpl examineService;

    /**
     * 提交加分项材料，保存照片，保存数据库
     *
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/subMaterials")
    public String subMaterials(@RequestParam(value = "materials") MultipartFile file, HttpServletRequest request) {
        // Shiro传入开始
        String classMajor = "计算机18-4";
        String number = "2018212602";
        String name = "江宇轩";
        // Shiro传入结束
        String type = request.getParameter("type");
        String level = request.getParameter("level");
        String awardName = request.getParameter("awardName");
        String sc = request.getParameter("score");
        int score = Integer.parseInt(sc);
        AwardTemp awardTemp = new AwardTemp(number, name, type, level, awardName, score, 0, "", classMajor);
        String info = examineService.insertAwardTemp(awardTemp, file);
        return "index";
    }

    /**
     * 管理员：审核材料->展示前台
     *
     * @param request
     * @return
     */
    @GetMapping("/showMaterials_admin")
    @ResponseBody
    public ArrayList<AwardTemp> showMaterials_admin(HttpServletRequest request) {
        String classMajor = "计算机18-4"; // 后期前端传入
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
    public ArrayList<AwardTemp> showMaterials_monitor(HttpServletRequest request) {
        String classMajor = "计算机18-4"; // 后期前端传入
        ArrayList<AwardTemp> awardTemps = new ArrayList<AwardTemp>();
        awardTemps = examineService.getAwardTempInfo(classMajor, 0);
        return awardTemps;
    }

    /**
     * 管理员审核
     *
     * @return
     */
    @GetMapping("/judgeMaterials_amin")
    public String judgeMaterials_admin(HttpServletRequest request) {
        String name = request.getParameter("name");
        String awardName = request.getParameter("awardName");
        String FLAG = request.getParameter("flag");
        String reason = request.getParameter("reason");
        int flag = 0;
        if (FLAG == "true") {
            flag = 2;
        } else {
            flag = -2;
        }
        String result = examineService.judgeMaterials_admin(name, awardName, flag, reason);
        System.out.println(result);
        return result;
    }

    /**
     * 班长审核
     *
     * @return
     */
    @GetMapping("/judgeMaterials_monitor")
    public String judgeMaterials_monitor(HttpServletRequest request) {
        String name = request.getParameter("name");
        String awardName = request.getParameter("awardName");
        String FLAG = request.getParameter("flag");
        String reason = request.getParameter("reason");
        int flag = 0;
        if (FLAG == "true") {
            flag = 1;
        } else {
            flag = -1;
        }
        String result = examineService.judgeMaterials_monitor(name, awardName, flag, reason);
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
    public String judgeResult(HttpServletRequest request, Model model) {
        String number = "2018212602"; // Shiro 传入
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
        String flag= null;
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

}
