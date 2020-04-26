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
import javax.servlet.ServletInputStream;
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
        String number=(String)request.getSession().getAttribute("number");
        List<qtScholarship> allqtaward=null;
        yxScholarship yxaward=null;
        allqtaward = showAwardslpml.findPersonAllByNumber(number);
        yxaward = showAwardslpml.Showyxawards(number);
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
    @RequestMapping(value="/BasicController/toEditPage",method = RequestMethod.POST)
    @ResponseBody
    public void toEditPage(@RequestBody(required=false) Model model,HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String number=(String)request.getSession().getAttribute("number");
        System.out.println(number);
        response.setCharacterEncoding("utf-8");
        basic ba=basicServicelpml.findbynumber(number);

        PrintWriter out=response.getWriter();
        out.print(ba);
    }

    //修改后basic信息先保存到temporary等待管理员确定

    @RequestMapping(value="/BasicController/addtemporary",method = RequestMethod.POST)
    @ResponseBody
    public void addtemporary(@RequestBody(required=false) basic s)
    {
        temporarybasic te=new temporarybasic(s.getNumber(),s.getName(),s.getSex(),s.getPolitical(),s.getDuty(),s.getClassMajor());
        String s1 = tempbasicServicelmpl.addtemporarybasic(te);
        System.out.println(s1);
    }

    //这里是管理员页面，所有用户想要修改的信息在页面显示出来，等待管理员确定或者否决
    //仿照你上面写的起睿
    @RequestMapping(value="/BasicController/getalltemporary",method = RequestMethod.POST)
    @ResponseBody
    public void getalltemporary(@RequestBody(required=false) HttpServletRequest request,HttpServletResponse response) throws IOException{
        List<temporarybasic> list=new ArrayList<>();
        list = tempbasicServicelmpl.findall();//查询所有需要修改的用户到页面，管理员确定则修改，否决则删除
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String jstr = "{}";
        for(temporarybasic st : list){

            jstr=jstr+","+"{"+"\""+"number"+"\""+":"+ "\""+ st.getNumber()+"\""+ ","
                    + "\""+"name"+"\""+":"+ "\""+ st.getName()+"\""+ ","
                    + "\""+"sex"+"\""+":"+ "\""+ st.getSex()+"\""+ ","
                    + "\""+"political"+"\""+":"+ "\""+ st.getPolitical()+"\""+ ","
                    + "\""+"duty"+"\""+":"+ "\""+ st.getDuty()+"\""+ ","
                    + "\""+"classMajor"+"\""+":"+ "\""+ st.getClassMajor()+"\""
                    +"}";
        }
        out.print("["+jstr+"]");
    }

    @RequestMapping(value="/BasicController/toEditbasicPage",method = RequestMethod.POST)
    @ResponseBody
    public void toEditbasicPage(@RequestBody(required=false) String a,temporarybasic te, HttpServletRequest request,HttpServletResponse response) throws IOException
    {

        String allow=request.getParameter("boolean");
        response.setCharacterEncoding("utf-8");
        System.out.print(allow);
        System.out.println(te);
        if(allow.equals("1")){//同意的话就修改
            basic ba=new basic(te.getNumber(),te.getName(),te.getSex(),te.getPolitical(),te.getDuty(),te.getClassMajor());
            basicServicelpml.updatabasic(ba,te.getNumber());
        }
        tempbasicServicelmpl.deleteByNumber(te.getNumber());
    }

}
