package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.HonorService;
import com.evaluation.system.domain.ExtraEntity.VerifyHonor;
import com.evaluation.system.domain.honor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/honor")
public class HonorController {

    @Autowired
    HonorService honorService;

    @PostMapping("/addhonor")
    public String addHonor(HttpServletRequest request, Model model){
        HttpSession session=request.getSession();
        String number=session.getAttribute("number").toString();
        String ho=request.getParameter("honor");
        String year=request.getParameter("year");
        honor honor=new honor(number,ho,year,0,"");
        boolean b = honorService.addHonor(honor);
        if(b){
            model.addAttribute("mes","申请成功");
        }else {
            model.addAttribute("mes","申请失败");
        }
        return "/scholarship/honorform.html";
    }

    @PostMapping("/deleteHonor")
    public String deleteHonor(String number,String year,String honor,int state){
        if(state<0){
            return "只能删除被否决的申请！";
        }
        int i=honorService.deleteHonor(number,honor,year,state);
        if(i==1){
            return "删除成功";
        }else {
            return "删除失败，请重试";
        }
    }

    @GetMapping("/findToGuan")
    @ResponseBody
    public List<VerifyHonor> findHonorToGuan(String year, HttpServletRequest request){
        String classmajor=request.getParameter("classmajor");
        if(classmajor==null){
            classmajor="计算机18-4";
        }
        System.out.println(classmajor);
        List<VerifyHonor> honor_list = honorService.findHonorByClassMajorAndYear(classmajor,1);
        return honor_list;
    }

    @PostMapping("/verifyAfterBan")
    @ResponseBody
    public String verifyAfterBan(HttpServletRequest request){
        String number=request.getParameter("number");
        String studentHonor=request.getParameter("studentHonor");
        String year=request.getParameter("year");
        int state=Integer.parseInt(request.getParameter("state"));
        String result=updateStateOfHonor(number,studentHonor,year,state);//返回的状态为1（通过），-1（不通过）
        return result;
    }

    @PostMapping("/verifyAfterGuan")
    @ResponseBody
    public String verifyAfterGuan(HttpServletRequest request){
        String number=request.getParameter("number");
        String studentHonor=request.getParameter("studentHonor");
        String year=request.getParameter("year");
        int state=Integer.parseInt(request.getParameter("state"));
        String result=updateStateOfHonor(number,studentHonor,year,state);//返回的状态为2（通过），-2（不通过）
        return result;
    }
    private String updateStateOfHonor(String number,String honor,String year,int state){
        int i = honorService.updateStateByNumAndHorAndYe(number, honor, year, state);
        if(i>0){
            return "审核通过";
        }
        return "审核已拒绝";
    }
}
