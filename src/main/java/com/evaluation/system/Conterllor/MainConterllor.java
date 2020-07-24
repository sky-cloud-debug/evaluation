package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.Impl.BasicServicelpml;
import com.evaluation.system.Service.fileService;
import com.evaluation.system.Service.loginService;
import com.evaluation.system.domain.basic;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainConterllor {

    @Autowired
    loginService loginService;
    @Autowired
    BasicServicelpml basicServicelpml;

    @Autowired
    fileService fileService;

    @GetMapping("index")
    public String login(){return "login/Login";}

    @GetMapping("/add")
    public String add(){
        return "table/add";
    }

    @PostMapping("/Login")
    public String Login(Model model,HttpServletRequest request){
        String number=request.getParameter("username");
        String password=request.getParameter("password");
        boolean str= loginService.login(number,password);
        if(!str){
            model.addAttribute("msg","用户名或密码错误");
            return "login/Login";
        }
        HttpSession session=request.getSession();
        session.setAttribute("number",number);
        basic b = basicServicelpml.findbynumber(number);
        String s=b.getClassMajor();
        String[] s1=s.split("\\d");
        int i=s1[0].length();
        String classMajorLike = null;
        classMajorLike=s.substring(0,i);
        System.out.println("cvccccccccccccc"+s);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa"+classMajorLike);
        session.setAttribute("classMajor",s);
        session.setAttribute("classMajorlike",classMajorLike);
        return "redirect:/index.html";
    }

    @PostMapping("/upfile")
    public String upfile(Model model,HttpServletRequest request) throws Exception{
        //每次上传某个excel前先将其在数据库中的对应的数据表的数据清空，此处没写，需要根据具体需要来写

        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest) request;


        MultipartFile file= multipartHttpServletRequest.getFile("filename");
        String filename=file.getOriginalFilename();
        String type=filename.substring(filename.indexOf(".")+1);
        if(!type.equals("xls")||type.equals("xlsx")){
            model.addAttribute("msg","文件类型只能是xls或者xlsx格式");
            return "";
        }
        if(file.isEmpty()){
            model.addAttribute("msg","文件不能为空");
            return "";
        }
        InputStream inputStream=file.getInputStream();
        List<List<Object>> list=fileService.getExcel(inputStream,file.getOriginalFilename());

        return "";
    }

    @PostMapping("/modifypsd")
    public String modify(HttpServletRequest request,Model model){
        String new_psd=request.getParameter("new_password");
        String again_psd=request.getParameter("again_password");

        if(!new_psd.equals(again_psd)) {
            model.addAttribute("msg","密码不同！");
            return "user/modifypsd";
        }

        return "success";
    }

    @GetMapping("rest")
    public String reset(){
        return "page/rest";
    }


}
