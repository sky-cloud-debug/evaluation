package com.evaluation.system.Conterllor.extraItem;

import com.evaluation.system.Service.*;
import com.evaluation.system.domain.Award;
import com.evaluation.system.domain.ExtraEntity.exportquality;
import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.honor;
import com.evaluation.system.domain.user;
import com.evaluation.system.util.ExcelUtils.EntityExcelUtil;
import com.evaluation.system.util.ExcelUtils.Excellmpl.awardExcelmpl;
import com.evaluation.system.util.ExcelUtils.Excellmpl.basicExceImpl;
import com.evaluation.system.util.ExcelUtils.Excellmpl.honorExcelmpl;
import com.evaluation.system.util.ExcelUtils.Excellmpl.qualityExceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Excel")
public class ExcelController {

    @Autowired
    userService userService;

    @Autowired
    BasicService basicService;

    @Autowired
    QuailtyService quailtyService;

    @Autowired
    AwardService awardService;

    @Autowired
    HonorService honorService;

    //导入用户信息
    @PostMapping("/importbasic")
    public String importbasic(HttpServletRequest request, ModelMap modelMap){
        EntityExcelUtil<basic> excelUtil=new basicExceImpl();
        String path=request.getParameter("path");
        List<basic> list = excelUtil.importExcel(path);
        int should=list.size();
        int all=0;
        StringBuilder s=new StringBuilder();
        for(basic a:list){
            boolean flag = basicService.addbasic(a);
            user u=new user(a.getNumber(),a.getNumber(),1);
            boolean flag1 = userService.adduser(u);
            if(flag&&flag1){
                all++;
            }else {
                s.append(a.getNumber()+" ");
            }
        }
        String mes="应导入用户"+should+"人，"+"实际导入用户"+all+"人,";
        if(all==should){
            mes+="用户全部导入完毕！";
        }else {
            mes+="未导入的学号为："+s;
        }
        modelMap.addAttribute("mes",mes);
        return "/imANDex/import.html";
    }

    //导入学生荣誉信息
    @PostMapping("/importhonor")
    public String importhonor(HttpServletRequest request, ModelMap modelMap){
        EntityExcelUtil<honor> excelUtil=new honorExcelmpl();
        String path=request.getParameter("path");
        List<honor> list = excelUtil.importExcel(path);
        int should=list.size();
        int all=0;
        StringBuilder s=new StringBuilder();
        for(honor a:list){
            boolean flag = honorService.addHonor(a);
            if(flag){
                all++;
            }else {
                s.append(a.getNumber()+" ");
            }
        }
        String mes="应导入数据"+should+"条，"+"实际导入数据"+all+"条,";
        if(all==should){
            mes+="用户全部导入完毕！";
        }else {
            mes+="未导入的学号为："+s;
        }
        modelMap.addAttribute("mes",mes);
        return "/imANDex/import.html";
    }

    //导出quality到Excel
    @PostMapping("/exportquality")
    public String exportquality(HttpServletRequest request, Model model){
        String path=request.getParameter("path");
        path+="\\quality.xlsx";
        EntityExcelUtil<exportquality> excelUtil=new qualityExceImpl();
        List<exportquality> all=quailtyService.exportquality();
        boolean i=excelUtil.exportExcel(all,path,"sheet1");
        if(i){
            model.addAttribute("mes","导出信息成功");
        }else {
            model.addAttribute("mes","导出信息失败");
        }
        return "/imANDex/export.html";
    }

    //导出Award到Excel
    @PostMapping("/exportAward")
    public String exportAward(HttpServletRequest request){
        String path=request.getParameter("path");
        HttpSession session=request.getSession();
        String number=session.getAttribute("number").toString();
        path+="\\award.xlsx";
        EntityExcelUtil<Award> excelUtil=new awardExcelmpl();
        List<Award> list=awardService.findByNumber(number);
        boolean i=excelUtil.exportExcel(list,path,"sheet1");
        if(i){
            return "导出信息成功！";
        }else {
            return "导出信息失败！";
        }
    }

}
