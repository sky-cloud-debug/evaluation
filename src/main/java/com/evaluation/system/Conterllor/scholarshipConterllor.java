package com.evaluation.system.Conterllor;

import com.evaluation.system.Dao.QtScholarshipRepository;
import com.evaluation.system.Dao.yxScholarshipRepository;
import com.evaluation.system.Service.ScholarshipService;
import com.evaluation.system.domain.qtScholarship;
import com.evaluation.system.domain.yxScholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/scholarship")
public class scholarshipConterllor {

    @Autowired
    ScholarshipService ScholarshipService;


    /**
     * 申报优秀学生奖学金
     * @create：song
     * @param request
     * @param model
     * @return
     */
    //这里建议使用@ResponseBody//直接将提示信息返回原来页面
    @PostMapping("/yxScholarship")
    public String yxScholarship(Model model,HttpServletRequest request){
        HttpSession session=request.getSession();
        String number=session.getAttribute("number").toString();
        int level=Integer.parseInt(request.getParameter("level"));
        String card=request.getParameter("card");
        String year=request.getParameter("year");
        yxScholarship yxScholarship=new yxScholarship(number,level,card,"",0,"");
        boolean flag= ScholarshipService.add_yx(yxScholarship);
        if(flag){
            return "scholarship/successful";//可选
        }
        model.addAttribute("msg","申请失败，请重试！");
        return "scholarship/error";//可选

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
        HttpSession session=request.getSession();
        String number=session.getAttribute("number").toString();
        String bonusName=request.getParameter("bonusName");
        String card=request.getParameter("card");
        String year=request.getParameter("year");
        qtScholarship qtScholarship=new qtScholarship(number,bonusName,card,year,0,"");
        boolean flag=ScholarshipService.add_qt(qtScholarship);
        if(flag){
            return "scholarship/successful";//可选
        }
        model.addAttribute("msg","申请失败，请重试！");
        return "scholarship/error";//可选
    }

    //管理员审核所有的奖项
    @GetMapping("/verifyqt")
    public String toEditPage(Model model,HttpServletRequest request){
//        String number=request.getParameter("number");
//        String bonus=request.getParameter("bonus");
//        qtScholarship qt = qtScholarshipRepository.findByNumberAndBonus_name(number,bonus);
//        model.addAttribute("scoring",qt);
//        //这一步是信息的回显，返回修改页面
//        return "scoring/modify1";
        return null;
    }
    //修改信息
    @PutMapping("/modifyqt")
    public String upqtscholarship(qtScholarship qt){
//        qtScholarshipRepository.save(qt);
//        return "redirect:/";//返回到查询奖项信息页或者成功页
        return null;
    }
}
