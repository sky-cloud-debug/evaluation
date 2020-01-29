package com.evaluation.system.Conterllor;

import com.evaluation.system.Dao.yxScholarshipRepository;
import com.evaluation.system.Service.yxScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
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
    yxScholarshipService yxScholarshipService;


    /**
     * 申报优秀学生奖学金
     * @create：song
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/yxScholarship")
    public String yxScholarship(Model model, HttpServletRequest request){
        String level=request.getParameter("interest");
        String card=request.getParameter("card");

        String information=yxScholarshipService.add_yx(level,card);
        if(information.equals("申请成功")){
            return "scholarship/successful";
        }
        return "";
    }

    /**
     * 申报其他奖学金
     * @create：song
     * @param model
     * @return
     */
    @GetMapping("/qtscholarship")
    public String qtscholarship(Model model){
        return null;
    }

}
