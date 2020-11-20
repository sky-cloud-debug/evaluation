package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.ScholarshipService;
import com.evaluation.system.domain.ExtraEntity.VerifyQtScholarship;
import com.evaluation.system.domain.ExtraEntity.VerifyYxScholarship;
import com.evaluation.system.domain.qtScholarship;
import com.evaluation.system.domain.yxScholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class ScholarshipConterllor {

    @Autowired
    ScholarshipService ScholarshipService;


    /**
     * 申报优秀学生奖学金
     * @create：song
     * @param request
     * @param
     * @return
     */
    //这里建议使用@ResponseBody//直接将提示信息返回原来页面
    @PostMapping("/yxScholarship")
    @ResponseBody
    public String yxScholarship(HttpServletRequest request){
        HttpSession session=request.getSession();
        String number=session.getAttribute("number").toString();
        int level=Integer.parseInt(request.getParameter("level"));
        String card=request.getParameter("card");
        String year=request.getParameter("year");
        yxScholarship yxScholarship=new yxScholarship(number,level,card,"",0,"");
        boolean flag= ScholarshipService.add_yx(yxScholarship);
        if(flag){
            return "申请成功";//可选
        }
        return "申请成功";//可选

    }

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

    //班长审核Qt奖学金
    @RequestMapping(value="/verifyQtbanZhang",method = RequestMethod.POST)
    @ResponseBody
    public void banZhangVerifyQt(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        String classmajor=session.getAttribute("classMajor").toString();
        System.out.println(classmajor);
        List<VerifyQtScholarship> list=ScholarshipService.verifyQtscholarshipByClass(classmajor,0);
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String jstr = "{}";
        for(VerifyQtScholarship st : list){

            jstr=jstr+","+"{"+"\""+"number"+"\""+":"+ "\""+ st.getNumber()+"\""+ ","
                    + "\""+"name"+"\""+":"+ "\""+ st.getName()+"\""+ ","
                    + "\""+"classMajor"+"\""+":"+ "\""+ st.getClassMajor()+"\""+ ","
                    + "\""+"bonus_name"+"\""+":"+ "\""+ st.getBonus_name()+"\""+ ","
                    + "\""+"card_number"+"\""+":"+ "\""+ st.getCard_number()+"\""+ ","
                    + "\""+"year"+"\""+":"+ "\""+ st.getYear()+"\""+ ","
                    + "\""+"reason"+"\""+":"+ "\""+ st.getReason()+"\""
                    +"}";

        }
        out.print("["+jstr+"]");
    }
    //管理员审核Qt奖学金
    @RequestMapping(value="/verifyQtGuanLi",method = RequestMethod.POST)
    @ResponseBody
    public void GuanLiVerifyQt(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String classMajor=request.getParameter("major");
        List<VerifyQtScholarship> list=ScholarshipService.verifyQtscholarshipByClass(classMajor,1);
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String jstr = "{}";
        for(VerifyQtScholarship st : list){

            jstr=jstr+","+"{"+"\""+"number"+"\""+":"+ "\""+ st.getNumber()+"\""+ ","
                    + "\""+"name"+"\""+":"+ "\""+ st.getName()+"\""+ ","
                    + "\""+"classMajor"+"\""+":"+ "\""+ st.getClassMajor()+"\""+ ","
                    + "\""+"bonus_name"+"\""+":"+ "\""+ st.getBonus_name()+"\""+ ","
                    + "\""+"card_number"+"\""+":"+ "\""+ st.getCard_number()+"\""+ ","
                    + "\""+"year"+"\""+":"+ "\""+ st.getYear()+"\""+ ","
                    + "\""+"reason"+"\""+":"+ "\""+ st.getReason()+"\""
                    +"}";

        }
        out.print("["+jstr+"]");
    }


    //班长审核Yx奖学金
    @RequestMapping(value="/verifyYxBanZhang",method = RequestMethod.POST)
    @ResponseBody
    public void banZhangVerifyYx(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        String classmajor=session.getAttribute("classMajor").toString();
        List<VerifyYxScholarship> list=ScholarshipService.verifyYxscholarshipByClass(classmajor,0);
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String jstr = "{}";
        for(VerifyYxScholarship st : list){
            jstr=jstr+","+"{"+"\""+"number"+"\""+":"+ "\""+ st.getNumber()+"\""+ ","
                    + "\""+"name"+"\""+":"+ "\""+ st.getName()+"\""+ ","
                    + "\""+"classMajor"+"\""+":"+ "\""+ st.getClassMajor()+"\""+ ","
                    + "\""+"bonus_name"+"\""+":"+ "\""+ st.getScholarshipLevel()+"\""+ ","
                    + "\""+"card_number"+"\""+":"+ "\""+ st.getCardNumber()+"\""+ ","
                    + "\""+"year"+"\""+":"+ "\""+ st.getYear()+"\""+ ","
                    + "\""+"reason"+"\""+":"+ "\""+ st.getReason()+"\""
                    +"}";
        }
        out.print("["+jstr+"]");
    }
    //管理员审核Yx奖学金

    @RequestMapping(value="/verifyYxGuanLi",method = RequestMethod.POST)
    @ResponseBody
    public void GuanLiVerifyYx(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String classMajor=request.getParameter("major");
        List<VerifyYxScholarship> list=ScholarshipService.verifyYxscholarshipByClass(classMajor,1);
        response.setCharacterEncoding("utf-8");
        PrintWriter out=response.getWriter();
        String jstr = "{}";
        for(VerifyYxScholarship st : list){
            jstr=jstr+","+"{"+"\""+"number"+"\""+":"+ "\""+ st.getNumber()+"\""+ ","
                    + "\""+"name"+"\""+":"+ "\""+ st.getName()+"\""+ ","
                    + "\""+"classMajor"+"\""+":"+ "\""+ st.getClassMajor()+"\""+ ","
                    + "\""+"bonus_name"+"\""+":"+ "\""+ st.getScholarshipLevel()+"\""+ ","
                    + "\""+"card_number"+"\""+":"+ "\""+ st.getCardNumber()+"\""+ ","
                    + "\""+"year"+"\""+":"+ "\""+ st.getYear()+"\""+ ","
                    + "\""+"reason"+"\""+":"+ "\""+ st.getReason()+"\""
                    +"}";
        }
        out.print("["+jstr+"]");

    }

    //班长审核Qt奖学金后修改状态
    @PostMapping("/BanAfterVerifyQt")
    @ResponseBody
    public String BanAfterVerifyQt(HttpServletRequest request){
        String number=request.getParameter("number");
        String year=request.getParameter("year");
        String bonusname=request.getParameter("bonusname");
        String reason=request.getParameter("reason");
        int state=Integer.parseInt(request.getParameter("flag"));
        return UpdateQtState(number,year,state,bonusname,reason);
    }
    //管理员审核Qt奖学金后修改状态
    @PostMapping("/GuanLiAfterVerifyQt")
    @ResponseBody
    public String GuanAfterVerifyQt(HttpServletRequest request){
        String number=request.getParameter("number");
        String year=request.getParameter("year");
        int state=Integer.parseInt(request.getParameter("flag"));
        String bonusname=request.getParameter("bonusname");
        String reason=request.getParameter("reason");
        return UpdateQtState(number,year,state,bonusname,reason);
    }
    private String UpdateQtState(String number,String year,int state,String bonusname,String reason){
        int i = ScholarshipService.updateQtScholarshipState(state, number, year,bonusname,reason);
        if(i==1){
            return "审核通过";
        }else {
            return "审核不通过";
        }
    }

    //班长审核Yx奖学金后修改状态
    @PostMapping("/BanAfterVerifyYx")
    @ResponseBody
    public String BanAfterVerifyYx(HttpServletRequest request){
        String number=request.getParameter("number");
        String year=request.getParameter("year");
        int state=Integer.parseInt(request.getParameter("flag"));
        String reason=request.getParameter("reason");
        return UpdateYxState(number,year,state,reason);
    }
    //管理员审核Yx奖学金后修改状态
    @PostMapping("/GuanLiAfterVerifyYx")
    @ResponseBody
    public String GuanAfterVerifyYx(HttpServletRequest request){
        String number=request.getParameter("number");
        String year=request.getParameter("year");
        int state=Integer.parseInt(request.getParameter("flag"));
        String reason=request.getParameter("reason");
        return UpdateYxState(number,year,state,reason);
    }
    private String UpdateYxState(String number,String year,int state,String reason){
        int i = ScholarshipService.updateYxScholarshipState(state, number, year,reason);
        if(i==1){
            return "审核通过";
        }else {
            return "审核不通过";
        }
    }
}
