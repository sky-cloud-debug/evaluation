package com.evaluation.system.Conterllor;

import com.evaluation.system.Dao.QtScholarshipRepository;
import com.evaluation.system.Dao.yxScholarshipRepository;
import com.evaluation.system.Service.ScholarshipService;
import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.qtScholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/scholarship")
public class scholarshipConterllor {

    @Autowired
    yxScholarshipRepository yxScholarshipRepository;

    @Autowired
    ScholarshipService ScholarshipService;

    @Autowired
    QtScholarshipRepository qtScholarshipRepository;

    @GetMapping("/yxscholarship")
    public String yx(){return "scholarship/yxform";}

    @GetMapping("/qtscholarship")
    public String qt(){return "scholarship/qtform";}

    /**
     * 申报优秀学生奖学金
     * @create：song
     * @param request
     * @param model
     * @return
     */
    @PostMapping("/yxScholarship")
    public String yxScholarship(Model model,HttpServletRequest request){
        String level=request.getParameter("interest");
        String card=request.getParameter("card");

        System.out.println(level+"====="+card+"----------");

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
    @PostMapping("/qtScholarship")
    public String qtscholarship(Model model,HttpServletRequest request){
        String bonusName=request.getParameter("bonusName");
        String card=request.getParameter("card");

        System.out.println(bonusName+"===="+card+"-----------------");

        String information=ScholarshipService.add_qt(bonusName,card);
        model.addAttribute("msg",information);
        if(information.equals("申请成功，等待审批")){
            return "scholarship/successful";
        }
        return "scholarship/error";
    }

    //下面是修改奖项
    //信息的回显
    @GetMapping("/modifyqt")
    public String toEditPage(Model model,HttpServletRequest request){
        String number=request.getParameter("number");
        String bonus=request.getParameter("bonus");
        qtScholarship qt = qtScholarshipRepository.findByNumberAndBonus_name(number,bonus);
        model.addAttribute("scoring",qt);
        //这一步是信息的回显，返回修改页面
        return "scoring/modify1";
    }
    //修改信息
    @PutMapping("/modifyqt")
    public String upqtscholarship(qtScholarship qt){
        qtScholarshipRepository.save(qt);
        return "redirect:/";//返回到查询奖项信息页或者成功页
    }
}
