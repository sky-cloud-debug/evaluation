package com.evaluation.system.Conterllor;

import com.evaluation.system.Dao.yxScholarshipRepository;
import com.evaluation.system.Service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/scholarship")
public class scholarshipConterllor {

    @Autowired
    yxScholarshipRepository yxScholarshipRepository;

    @Autowired
    ScholarshipService ScholarshipService;

    @GetMapping("/yxscholarship")
    public String yx(){return "scholarship/yxform";}

    /**
     * 申报优秀学生奖学金
     * @create：song
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/yxScholarship")
    public String yxScholarship(Model model,HttpServletRequest request){
        String level=request.getParameter("interest");
        String card=request.getParameter("card");

        String information= ScholarshipService.add_yx(level,card);
        model.addAttribute("msg",information);
        if(information.equals("申请成功，等待审批")){
            return "scholarship/successful";
        }
        return "scholarship/error";
    }
    /**
     * 申报其他奖学金
     * @create：song
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/qtscholarship")
    public String qtscholarship(Model model,HttpServletRequest request){
        String bonusName=request.getParameter("bonusName");
        String card=request.getParameter("card");

        String information=ScholarshipService.add_qt(bonusName,card);
        model.addAttribute("msg",information);
        if(information.equals("申请成功，等待审批")){
            return "scholarship/successful";
        }
        return "scholarship/error";
    }

}
