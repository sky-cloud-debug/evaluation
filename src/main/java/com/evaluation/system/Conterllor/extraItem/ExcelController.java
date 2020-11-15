package com.evaluation.system.Conterllor.extraItem;

import com.evaluation.system.Service.BasicService;
import com.evaluation.system.Service.QuailtyService;
import com.evaluation.system.Service.userService;
import com.evaluation.system.domain.ExtraEntity.exportquality;
import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.user;
import com.evaluation.system.util.ExcelUtils.EntityExcelUtil;
import com.evaluation.system.util.ExcelUtils.Excellmpl.basicExceImpl;
import com.evaluation.system.util.ExcelUtils.Excellmpl.qualityExceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @PostMapping("/importbasic")
    public String importbasic(HttpServletRequest request, RedirectAttributesModelMap modelMap){
        EntityExcelUtil<basic> excelUtil=new basicExceImpl();
        String path=request.getParameter("path");
        List<basic> list = excelUtil.importExcel(path);
        int should=list.size();
        List<String> number=new ArrayList<>();
        int all=0;
        for(basic a:list){
            boolean flag = basicService.addbasic(a);
            user u=new user(a.getNumber(),a.getNumber(),1);
            boolean flag1 = userService.adduser(u);
            if(flag&&flag1){
                all++;
            }else {
                number.add(a.getNumber());
            }
        }
        if(all==should){
            return "成员全部导入完毕!";
        }else {
            modelMap.addFlashAttribute("number",number);
            return null;//需要返回的页面
        }
    }
    @PostMapping("/exportquality")
    public String exportquality(HttpServletRequest request){
        String path=request.getParameter("path");
        path+="\\quality.xlsx";
        EntityExcelUtil<exportquality> excelUtil=new qualityExceImpl();
        List<exportquality> all=quailtyService.exportquality();
        excelUtil.exportExcel(all,path,"sheet1");
        return "导出信息成功！";
    }

}
