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
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * author:江宇轩
 */
@Controller
public class ExamineController {

    @Autowired
    ExamineServiceImpl examineService;

    @GetMapping("/tosubmit")
    public String login(){return "/xywjyx/submit2";}
    /**
     * 提交加分项材料，保存照片，保存数据库
     *
     * @param file
     * @param request
     * @return
     */

    @PostMapping("/subMaterials")
    public String subMaterials(@RequestParam(value = "materials") MultipartFile file, HttpServletRequest request,RedirectAttributesModelMap model) {
        // Shiro传入开始
        HttpSession session=request.getSession();
        String classMajor = session.getAttribute("classMajor").toString();
        String number = session.getAttribute("number").toString();
        String name = session.getAttribute("name").toString();
        System.out.println(classMajor+number+name);
        // Shiro传入结束
        String type = request.getParameter("type");
        String level = request.getParameter("level");
        String awardName = request.getParameter("awardName");
        String sc = request.getParameter("score");
        int score = Integer.parseInt(sc);
        AwardTemp awardTemp = new AwardTemp(number, name, type, level, awardName, "time",score, 0, "", classMajor,"route");
        String info=null;
        try {
            info = examineService.insertAwardTemp(awardTemp, file,request);
            model.addFlashAttribute("mes",info);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/tosubmit";
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
        HttpSession session=request.getSession();
        String classMajor = session.getAttribute("classMajor").toString(); // 后期前端传入
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
        HttpSession session=request.getSession();
        String classMajor = session.getAttribute("classMajor").toString();
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
        return result;
    }

    /**
     * 班长审核
     *
     * @return
     */
    @GetMapping("/judgeMaterials_monitor")
    public String judgeMaterials_monitor(HttpServletRequest request) {
        String name = request.getParameter("number");
        String awardName = request.getParameter("awardName");
        String FLAG = request.getParameter("flag");
        String reason = request.getParameter("reason");
        int flag = 0;
        if (FLAG.equals("true")) {
            flag = 1;
        } else {
            flag = -1;
        }
        String result = examineService.judgeMaterials_monitor(name, awardName, flag, reason,request);
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
        HttpSession session=request.getSession();
        String number = session.getAttribute("number").toString();; // Shiro 传入
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
