package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.BasicService;
import com.evaluation.system.Service.HonorService;
import com.evaluation.system.Service.ScholarshipService;
import com.evaluation.system.Service.TembasicService;
import com.evaluation.system.domain.ExtraEntity.AllqtAwards;
import com.evaluation.system.domain.ExtraEntity.AllxyAwards;
import com.evaluation.system.domain.ExtraEntity.ShowStu;
import com.evaluation.system.domain.ExtraEntity.temporarybasic;
import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.honor;
import com.evaluation.system.domain.qtScholarship;
import com.evaluation.system.domain.yxScholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class BasicController {

    @Autowired
    BasicService basicService;

    @Autowired
    ScholarshipService scholarshipService;

    @Autowired
    TembasicService tembasicService;

    @Autowired
    HonorService honorService;


    //这里是精准查询，查询全班的，需要删去智育成绩，和总分。
//    @RequiresRoles(value = {"student","monitor"},logical = Logical.OR)
    @RequestMapping(value="/BasicController/insertUserInfo",method = RequestMethod.POST)
    @ResponseBody
    public void insertUserInfo(@RequestBody(required=false)Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("来到公式信息");
        String classMajor=(String)request.getSession().getAttribute("classMajor");
        List<ShowStu> list= basicService.ShowScore(classMajor);
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
//    @RequiresRoles(value = {"student","monitor"},logical = Logical.OR)
    @RequestMapping(value="/BasicController/showawards",method = RequestMethod.POST)
    @ResponseBody
    public void showawards(@RequestBody(required=false) Model model, HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("个人 获奖情况查询");
        String number=(String)request.getSession().getAttribute("number");
        List<qtScholarship> allqtaward=scholarshipService.findqtByNumber(number);
        List<yxScholarship> allyxaward=scholarshipService.findyxByNumber(number);
        List<honor> honorlist = honorService.findByNumber(number);
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String jstr1 = "{}";
        String jstr2 = "{}";
        String jstr3 = "{}";
        for(yxScholarship i : allyxaward){
            jstr1=jstr1+","+"{"+"\""+"number"+"\""+":"+ "\""+ i.getNumber()+"\""+ ","
                    + "\""+"card"+"\""+":"+ "\""+ i.getCardNumber()+"\""+ ","
                    + "\""+"scholarshipLevel"+"\""+":"+ "\""+ i.getScholarshipLevel()+"\""+ ","
                    + "\""+"state"+"\""+":"+ "\""+PanDuan(i.getState()) +"\""+ ","
                    + "\""+"year"+"\""+":"+ "\""+i.getYear() +"\""+ ","
                    + "\""+"reason"+"\""+":"+ "\""+i.getReason() +"\""
                    +"}";
        }
        for(qtScholarship i : allqtaward){
            jstr2=jstr2+","+"{"+"\""+"number"+"\""+":"+ "\""+ i.getNumber()+"\""+ ","
                    + "\""+"card"+"\""+":"+ "\""+ i.getCard_number()+"\""+ ","
                    + "\""+"bonus_name"+"\""+":"+ "\""+ i.getBonus_name()+"\""+ ","
                    + "\""+"state"+"\""+":"+ "\""+PanDuan(i.getState() )+"\""+ ","
                    + "\""+"year"+"\""+":"+ "\""+i.getYear() +"\""+ ","
                    + "\""+"reason"+"\""+":"+ "\""+i.getReason() +"\""
                    +"}";
        }
        for(honor i : honorlist){
            jstr3=jstr3+","+"{"+"\""+"number"+"\""+":"+ "\""+ i.getNumber()+"\""+ ","
                    + "\""+"studentHonor"+"\""+":"+ "\""+ i.getStudentHonor()+"\""+ ","
                    + "\""+"state"+"\""+":"+ "\""+PanDuan(i.getState() )+"\""+ ","
                    + "\""+"year"+"\""+":"+ "\""+i.getYear() +"\""+ ","
                    + "\""+"reason"+"\""+":"+ "\""+i.getReason() +"\""
                    +"}";
        }

        out.print("["+jstr1+"]"+"-"+"["+jstr2+"]"+"-"+"["+jstr3+"]");
    }

    private  String PanDuan(int i){
        String states=null;
        if(i==0){
            states="未审核";
        }else if(i==1){
            states="班长审核通过";
        }else if(i==-1){
            states="班长审核未通过";
        }else if(i==2){
            states="审核通过";
        }else {
            states="管理员审核未通过";
        }
        return states;
    }
    @RequestMapping(value="/BasicController/qtAward",method = RequestMethod.POST)
    @ResponseBody
    public void qtAward(@RequestBody(required=false)String year,HttpServletResponse response,HttpServletRequest request) throws IOException {
        HttpSession session=request.getSession();
        String classmajor=session.getAttribute("classMajor").toString();
        String term=request.getParameter("year");
        List<AllqtAwards> list= scholarshipService.findqtByYearAndClassMajor(term,classmajor);
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String jstr = "{}";
        for(AllqtAwards st : list){
            jstr=jstr+","+"{"+"\""+"number"+"\""+":"+ "\""+ st.getNumber()+"\""+ ","
                    + "\""+"name"+"\""+":"+ "\""+ st.getName()+"\""+ ","
                    + "\""+"bonus_name"+"\""+":"+ "\""+ st.getBonus_name()+"\""
                    +"}";
        }
        out.print("["+jstr+"]");
    }

    @RequestMapping(value="/BasicController/yxAward",method = RequestMethod.POST)
    @ResponseBody
    public void yxAward(@RequestBody(required=false)String year,HttpServletResponse response,HttpServletRequest request) throws IOException {
        String term=request.getParameter("year");
        HttpSession session=request.getSession();
        String classmajor=session.getAttribute("classMajor").toString();
        List<AllxyAwards> list=scholarshipService.findyxByYearAndClassMajor(term,classmajor);
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String jstr = "{}";
        for(AllxyAwards st : list){

            jstr=jstr+","+"{"+"\""+"number"+"\""+":"+ "\""+ st.getNumber()+"\""+ ","
                    + "\""+"name"+"\""+":"+ "\""+ st.getName()+"\""+ ","
                    + "\""+"scholarshipLevel"+"\""+":"+ "\""+ st.getScholarshipLevel()+"\""
                    +"}";

        }
        out.println("["+jstr+"]");
    }

    //下面是修改信息的部分
    @RequestMapping(value="/BasicController/toEditPage",method = RequestMethod.POST)
    @ResponseBody
    public void toEditPage(@RequestBody(required=false) Model model,HttpServletRequest request,HttpServletResponse response) throws IOException
    {
        String number=(String)request.getSession().getAttribute("number");
        response.setCharacterEncoding("utf-8");
        basic ba= basicService.findByNumber(number);
        PrintWriter out=response.getWriter();
        out.print(ba);
    }

    //修改后basic信息先保存到temporary等待管理员确定
    @RequestMapping(value="/BasicController/addtemporary",method = RequestMethod.POST)
    @ResponseBody
    public String addtemporary(@RequestBody(required=false) basic s)
    {
        temporarybasic te=new temporarybasic(s.getNumber(),s.getName(),s.getSex(),s.getPolitical(),s.getDuty(),s.getClassMajor());
        boolean s1 = tembasicService.addtemporarybasic(te);
        if(s1){
            return "申请已经提交";
        }else {
            return "申请失败";
        }
    }

    //这里是管理员页面，所有用户想要修改的信息在页面显示出来，等待管理员确定或者否决
    //仿照你上面写的起睿
    @RequestMapping(value="/BasicController/getalltemporary",method = RequestMethod.POST)
    @ResponseBody
    public void getalltemporary(@RequestBody(required=false) HttpServletRequest request,HttpServletResponse response) throws IOException{
        List<temporarybasic> list = tembasicService.findall();
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
        if(allow.equals("1")){//同意的话就修改
            basic ba=new basic(te.getNumber(),te.getName(),te.getSex(),te.getPolitical(),te.getDuty(),te.getClassMajor());
            basicService.updatebasic(ba);
        }
        tembasicService.deleteByNumber(te.getNumber());
    }
}
