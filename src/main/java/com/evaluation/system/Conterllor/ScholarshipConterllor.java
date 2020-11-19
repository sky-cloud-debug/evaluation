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
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/scholarship")
public class ScholarshipConterllor {

    @Autowired
    ScholarshipService ScholarshipService;

    @PostMapping("/hello")
    @ResponseBody
    public String hello(){
        return "你好";
    }
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
    @GetMapping("/verifyQtbanZhang")
    public String banZhangVerifyQt(HttpServletRequest request){
        HttpSession session=request.getSession();
        String classmajor=session.getAttribute("classMajor").toString();
        List<VerifyQtScholarship> list=ScholarshipService.verifyQtscholarshipByClass(classmajor,0);
        return "scoring/verifyqt";
    }
    //管理员审核Qt奖学金
    @GetMapping("/verifyQtGuanLi")
    public String GuanLiVerifyQt(Model model,HttpServletRequest request){
        String classMajor=request.getAttribute("classmajor").toString();//页面传回
        List<VerifyQtScholarship> list=ScholarshipService.verifyQtscholarshipByClass("计算机18-4",1);//这里模糊
        return "scoring/verifyqt";
    }
    //班长审核Yx奖学金
    @GetMapping("/verifyYxBanZhang")
    public String banZhangVerifyYx(HttpServletRequest request){
        HttpSession session=request.getSession();
        String classmajor=session.getAttribute("classMajor").toString();
        List<VerifyYxScholarship> list=ScholarshipService.verifyYxscholarshipByClass(classmajor,0);
        return "scoring/verifyqt";
    }
    //管理员审核Yx奖学金
    @GetMapping("/verifyYxGuanLi")
    public String GuanLiVerifyYx(Model model,HttpServletRequest request){
        String classMajor=request.getAttribute("classmajor").toString();//页面传回
        List<VerifyYxScholarship> list=ScholarshipService.verifyYxscholarshipByClass(classMajor,1);//模糊
        return "scoring/verifyqt";
    }

    //班长审核Qt奖学金后修改状态
    @PostMapping("/BanAfterVerifyQt")
    @ResponseBody
    public String BanAfterVerifyQt(HttpServletRequest request){
        String number=request.getParameter("number");
        String year=request.getParameter("year");
        String bonusname=request.getParameter("bonusname");
        String reason=request.getParameter("reason");
        String s=number+" "+year+" "+bonusname+" "+reason;
        int state=Integer.parseInt(request.getParameter("flag"));//返回1(审核通过)，-1（审核不通过）
        return UpdateQtState(number,year,state,bonusname,reason);
    }
    //管理员审核Qt奖学金后修改状态
    @PostMapping("/GuanLiAfterVerifyQt")
    public String GuanAfterVerifyQt(HttpServletRequest request){
        String number=request.getAttribute("number").toString();
        String year=request.getAttribute("year").toString();
        String bonusname=request.getAttribute("bonusname").toString();
        String reason=request.getParameter("reason");
        int state=Integer.parseInt(request.getAttribute("flag").toString());//返回2（审核通过），-2（不通过）
        return UpdateQtState(number,year,state,bonusname,reason);
    }
    private String UpdateQtState(String number,String year,int state,String bonusname,String reaason){
        int i = ScholarshipService.updateQtScholarshipState(state, number, year,bonusname,reaason);
        if(i==1){
            return "审核通过";
        }else {
            return "审核不通过";
        }
    }

    //班长审核Yx奖学金后修改状态
    @PostMapping("/BanAfterVerifyYx")
    public String BanAfterVerifyYx(HttpServletRequest request){
        String number=request.getAttribute("number").toString();
        String year=request.getAttribute("year").toString();
        String reason=request.getParameter("reason");
        int state=Integer.parseInt(request.getAttribute("flag").toString());//返回1(审核通过)，-1（审核不通过）
        return UpdateYxState(number,year,state,reason);
    }
    //管理员审核Yx奖学金后修改状态
    @PostMapping("/GuanLiAfterVerifyYx")
    public String GuanAfterVerifyYx(HttpServletRequest request){
        String number=request.getAttribute("number").toString();
        String year=request.getAttribute("year").toString();
        String reason=request.getParameter("reason");
        int state=Integer.parseInt(request.getAttribute("flag").toString());//返回2（审核通过），-2（不通过）
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
