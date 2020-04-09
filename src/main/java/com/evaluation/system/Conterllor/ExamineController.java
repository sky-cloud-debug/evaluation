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
 * author 江宇轩
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
        String awardName = request.getParameter("awardName");
        String sc = request.getParameter("score");
        int score = Integer.parseInt(sc);
        AwardTemp awardTemp = new AwardTemp(number, name, type, awardName, score, "未审批", "", classMajor);
        String info = examineService.insertAwardTemp(awardTemp, file);
        return "index";
    }

    /**
     * 审核材料->展示前台
     *
     * @param request
     * @return
     */
    @GetMapping("/showMaterials")
    @ResponseBody
    public ArrayList<AwardTemp> showMaterials(HttpServletRequest request) {
        String classMajor = "计算机18-4"; // 后期前端传入
        ArrayList<AwardTemp> awardTemps = new ArrayList<AwardTemp>();
        awardTemps = examineService.getAwardTempInfo(classMajor, "未审批");
        return awardTemps;
    }

    /**
     * 管理员审核
     *
     * @return
     */
    @GetMapping("/judgeMaterials")
    public String judgeMaterials(HttpServletRequest request) {
        String name = request.getParameter("name");
        String awardName = request.getParameter("awardName");
        String flag = request.getParameter("flag");
        String reason = request.getParameter("reason");
        boolean judge;
        if (flag.equals("true")) {
            judge = true;
        } else {
            judge = false;
        }
        String result = examineService.judgeMaterials(name, awardName, judge, reason);
        System.out.println(result);
        return result;
    }

    /**
     * 展示所有驳回的加分项
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/showReject")
    public String showReject(HttpServletRequest request, Model model) {
        ArrayList<AwardTemp> awardTemps = examineService.getAwardTempRejectInfo("驳回 ");
        model.addAttribute("awardTemp", awardTemps);
        return "scoring/materials";
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

}
