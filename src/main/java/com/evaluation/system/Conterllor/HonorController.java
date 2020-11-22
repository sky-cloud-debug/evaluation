package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.HonorService;
import com.evaluation.system.domain.ExtraEntity.VerifyHonor;
import com.evaluation.system.domain.honor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        String year=request.getParameter("year");
        String honor=request.getParameter("honor");
        boolean b = honorService.addHonor(new honor(number,honor,year,0,""));
        if(b){
            model.addAttribute("mes","申请成功");
        }else {
            model.addAttribute("mes","申请失败");
        }
        return "/scholarship/honorform.html";
    }

    private String PanDuan(int state){
        if(state==1){
            return "班长审核通过";
        }else if(state==-1){
            return "班长审核失败";
        }else if(state==2){
            return "管理员审核通过";
        }else {
            return "管理员审核失败";
        }
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

    @GetMapping("/findToBan")
    public List<VerifyHonor> findHonorToBan(HttpServletRequest request){
        HttpSession session=request.getSession();
        String classmajor=session.getAttribute("classMajor").toString();
        List<VerifyHonor> honor_list = honorService.findHonorByClassMajorAndYear(classmajor,0);
        return honor_list;
    }

    @GetMapping("/findToGuan")
    public List<VerifyHonor> findHonorToGuan(String year,String classmajor, HttpServletRequest request){
        List<VerifyHonor> honor_list = honorService.findHonorByClassMajorAndYear(classmajor, 1);
        return honor_list;
    }

    @PostMapping("/verifyAfterBan")
    public String verifyAfterBan(String number,String honor,String year,int state,HttpServletRequest request){
        String result=updateStateOfHonor(number,honor,year,state);//返回的状态为1（通过），-1（不通过）
        return result;
    }

    @PostMapping("/verifyAfterGuan")
    public String verifyAfterGuan(String number,String honor,String year,int state,HttpServletRequest request){
        String result=updateStateOfHonor(number,honor,year,state);//返回的状态为2（通过），-2（不通过）
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
