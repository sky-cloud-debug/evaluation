package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.Impl.BasicServicelpml;
import com.evaluation.system.Service.Impl.ShowAwardslpml;
import com.evaluation.system.Service.Impl.TempbasicServicelmpl;
import com.evaluation.system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.*;

import javax.management.monitor.MonitorSettingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BasicController {

    @Autowired
    private BasicServicelpml basicServicelpml;

    @Autowired
    ShowAwardslpml showAwardslpml;

    @Autowired
    TempbasicServicelmpl tempbasicServicelmpl;


    @RequestMapping(value="/BasicController/insertUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public void insertUserInfo(@RequestBody(required=false) String name,HttpServletResponse response) throws IOException {

        List<ShowStu> list=new ArrayList<ShowStu>();
        list=basicServicelpml.ShowScore();
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String jstr = "{}";
        for(ShowStu st : list){

            jstr=jstr+","+"{"+"\""+"number"+"\""+":"+ "\""+ st.getNumber()+"\""+ ","
                            + "\""+"name"+"\""+":"+ "\""+ st.getName()+"\""+ ","
                            + "\""+"sex"+"\""+":"+ "\""+ st.getSex()+"\""+ ","
                            + "\""+"class_major"+"\""+":"+ "\""+ st.getClass_major()+"\""+ ","
                            + "\""+"moral"+"\""+":"+ "\""+ st.getMoral()+"\""+ ","
                            + "\""+"wisdom"+"\""+":"+ "\""+ st.getWisdom()+"\""+ ","
                            + "\""+"heart"+"\""+":"+ "\""+ st.getHeart()+"\""+ ","
                            + "\""+"technology"+"\""+":"+ "\""+ st.getTechnology()+"\""+ ","
                            + "\""+"total_count"+"\""+":"+ "\""+ st.getTotal_count()+"\""
                    +"}";

        }
        out.print("["+jstr+"]");

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


    @RequestMapping(value="/BasicController/qtAward",method = RequestMethod.POST)
    @ResponseBody
    public void qtAward(@RequestBody(required=false) String name,HttpServletResponse response) throws IOException {

        List<AllqtAwards> list=new ArrayList<AllqtAwards>();
        list=showAwardslpml.findAllqtAwards();
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String jstr = "{}";
        for(AllqtAwards st : list){

            jstr=jstr+","+"{"+"\""+"number"+"\""+":"+ "\""+ st.getNumber()+"\""+ ","
                    + "\""+"name"+"\""+":"+ "\""+ st.getName()+"\""+ ","
                    + "\""+"class_major"+"\""+":"+ "\""+ st.getClass_major()+"\""+ ","
                    + "\""+"bonus_name"+"\""+":"+ "\""+ st.getBonus_name()+"\""
                    +"}";

        }
        out.print("["+jstr+"]");
    }

    @RequestMapping(value="/BasicController/yxAward",method = RequestMethod.POST)
    @ResponseBody
    public void yxAward(@RequestBody(required=false) String name,HttpServletResponse response) throws IOException {

        List<AllxyAwards> list=new ArrayList<AllxyAwards>();
        list=showAwardslpml.findAllyxAwards();
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String jstr = "{}";
        for(AllxyAwards st : list){

            jstr=jstr+","+"{"+"\""+"number"+"\""+":"+ "\""+ st.getNumber()+"\""+ ","
                    + "\""+"name"+"\""+":"+ "\""+ st.getName()+"\""+ ","
                    + "\""+"class_major"+"\""+":"+ "\""+ st.getClass_major()+"\""+ ","
                    + "\""+"scholarshipLevel"+"\""+":"+ "\""+ st.getScholarshipLevel()+"\""
                    +"}";
        }
        out.print("["+jstr+"]");
    }

    //下面是修改信息的部分
    @GetMapping("basicmodify")
    public String toEditPage(Model model,HttpServletRequest request){
        String number=request.getParameter("number");
        basic ba=basicServicelpml.findbynumber(number);
        model.addAttribute("basic",ba);

        //这一步是信息的回显，返回修改页面将待修改的信息显示出来
        return "basic/modify";
    }

    //修改后basic信息先保存到temporary等待管理员确定
    @PostMapping("/addtemporary")
    public String addtemporary(temporarybasic te){
        String s = tempbasicServicelmpl.addtemporarybasic(te);
        return s;
    }
    //这里是管理员页面，所有用户想要修改的信息在页面显示出来，等待管理员确定或者否决
    //仿照你上面写的起睿
    @GetMapping("/getalltemporary")
    public void getalltemporary(@RequestBody(required=false)Model model,HttpServletResponse response) throws IOException{
        List<temporarybasic> list=new ArrayList<>();
        list = tempbasicServicelmpl.findall();//查询所有需要修改的用户到页面，管理员确定则修改，否决则删除
        if(list!=null){
            model.addAttribute("msg",list);
        }else {
            model.addAttribute("msg","读取信息错误！");
        }
        response.setCharacterEncoding("utf-8");

    }

    @PostMapping("/updatebasic")
    public String toEditbasicPage(HttpServletRequest request,boolean ok){
        String number=request.getParameter("number");
        temporarybasic te = tempbasicServicelmpl.findtemporarybasic(number);//先查出来要修改的人
        if(ok){//同意的话就修改
            basic ba=new basic(te.getNumber(),te.getName(),te.getSex(),te.getPolitical(),te.getDuty(),te.getClassMajor());
            basicServicelpml.updatabasic(ba,number);
        }
        //无论同意还是不，都删除这个请求修改信息
        tempbasicServicelmpl.deleteByNumber(number);
        return "redirect:/";//返回到一个提示修改成功的页面，或者啥也不干
    }

}
