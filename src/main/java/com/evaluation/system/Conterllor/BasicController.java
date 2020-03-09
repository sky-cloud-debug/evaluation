package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.Impl.BasicServicelpml;
import com.evaluation.system.Service.Impl.ShowAwardslpml;
import com.evaluation.system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BasicController {

    @Autowired
    private BasicServicelpml basicServicelpml;

    @Autowired
    ShowAwardslpml showAwardslpml;

    //这是所有人的得分情况，包含基本信息
    @GetMapping("stulists")
    public String showUsers(Model model){
        List<ShowStu> list=new ArrayList<ShowStu>();
        list=basicServicelpml.ShowScore();
        if(list!=null){
            model.addAttribute("msg",list);
        }else {
            model.addAttribute("msg","读取信息错误！");
        }
        return "ShowMessage";//返回到这个页面，现在还没有
    }

    //这是查询个人的获奖情况不知道是否用的到，随手写上了
    @GetMapping("personaward")
    public String showawards(Model model, HttpServletRequest request){
        String number=request.getParameter("number");
        basic student=null;
        qtScholarship qtawards=null;
        yxScholarship yxaward=null;
        student = showAwardslpml.ShowStudent(number);
        qtawards = showAwardslpml.Showqtawards(number);
        yxaward = showAwardslpml.Showyxawards(number);
        if(student!=null){
            model.addAttribute("student",student);
            if(qtawards==null&&yxaward==null){
                model.addAttribute("msg","没有得奖");
            }else {
                if(qtawards!=null){
                    model.addAttribute("qtawards",qtawards);
                }
                if(yxaward!=null){
                    model.addAttribute("yxaward",yxaward);
                }
            }
        }else {
            model.addAttribute("msg","没有该用户");
        }
        return "perAward";//返回个人的获奖情况
    }

    //这里是返回所有人除奖学金的奖项
    @GetMapping("AllAwardqtlist")
    public String showAllqtAwards(Model model){
        List<AllqtAwards> list=new ArrayList<AllqtAwards>();
        list=showAwardslpml.findAllqtAwards();
        if(list!=null){
            model.addAttribute("msg",list);
        }else {
            model.addAttribute("msg","读取信息错误！");
        }
        return "ShowqtAwards";//返回到这个页面，现在还没有
    }

    @GetMapping("AllAwardyxlist")
    public String showAllyxAwards(Model model){
        List<AllxyAwards> list=new ArrayList<AllxyAwards>();
        list=showAwardslpml.findAllyxAwards();
        if(list!=null){
            model.addAttribute("msg",list);
        }else {
            model.addAttribute("msg","读取信息错误！");
        }
        return "ShowyxAwards";//返回到这个页面，现在还没有
    }

    //下面是修改信息的部分
    @GetMapping("/scoring")
    public String toEditPage(Model model,HttpServletRequest request){
        String number=request.getParameter("number");
        basic ba=basicServicelpml.findbynumber(number);
        model.addAttribute("scoring",ba);

        //这一步是信息的回显，返回修改页面
        return "scoring/modify";
    }
    //修改basic信息
    @PutMapping("/scoring")
    public String updatabasic(basic ba){
        basicServicelpml.updatabasic(ba);
        return "redirect:/";//返回到一个提示修改成功的页面，或者返回到展示个人信息的页面
    }
}
