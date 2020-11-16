package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.HonorService;
import com.evaluation.system.domain.ExtraEntity.VerifyHonor;
import com.evaluation.system.domain.honor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/honor")
public class HonorController {

    @Autowired
    HonorService honorService;

    @PostMapping("/addhonor")
    public String addHonor(honor honor){
        boolean b = honorService.addHonor(honor);
        String result=null;
        if(b){
            result="申请成功";
        }else {
            result="申请失败";
        }
        return result;
    }

    @PostMapping("/deleteHonor")
    public String deleteHonor(String number,String year,String honor){
        int i=honorService.deleteHonor(number,honor,year);
        if(i==1){
            return "删除成功";
        }else {
            return "删除失败，请重试";
        }
    }

    @GetMapping("/findToBan")
    public List<VerifyHonor> findHonorToBan(String year, HttpServletRequest request){
        HttpSession session=request.getSession();
        String classmajor=session.getAttribute("classMajor").toString();
        List<VerifyHonor> honor_list = honorService.findHonorByClassMajorAndYear(classmajor, year,0);
        return honor_list;
    }

    @GetMapping("/findToGuan")
    public List<VerifyHonor> findHonorToGuan(String year,String classmajor, HttpServletRequest request){
        List<VerifyHonor> honor_list = honorService.findHonorByClassMajorAndYear(classmajor, year,1);
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
