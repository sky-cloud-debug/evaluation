package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.Impl.BasicServicelmpl;
import com.evaluation.system.Service.fileService;
import com.evaluation.system.Service.loginService;
import com.evaluation.system.domain.basic;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

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
    BasicServicelmpl basicServicelmpl;

    @Autowired
    fileService fileService;

    @GetMapping("index")
    public String login(){return "login/Login";}

    @GetMapping("/add")
    public String add(){
        return "table/add";
    }

    @RequestMapping("/logout")
    public String logout(){
        System.out.println("来到退出登录");
        Subject subject=SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/main/index";
    }


    @PostMapping("/Login")
    public String Login(HttpServletRequest request, RedirectAttributesModelMap model){
        String number=request.getParameter("username");
        String password=request.getParameter("password");
        Subject subject= SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(number,password));
            HttpSession session=request.getSession();
            session.setAttribute("number",number);
            basic b = basicServicelmpl.findbynumber(number);
            String s=b.getClassMajor();
            String[] s1=s.split("\\d");
            int i=s1[0].length();
            String classMajorLike = null;
            classMajorLike=s.substring(0,i);
            session.setAttribute("classMajor",s);
            session.setAttribute("number",number);
            session.setAttribute("name",b.getName());
            session.setAttribute("classMajorlike",classMajorLike);
            return "redirect:/index.html";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            model.addFlashAttribute("mes","用户名错误！");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            model.addFlashAttribute("mes","密码错误！");
        }
        return "redirect:/main/index";
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

    @GetMapping("/rest")
    public String reset(){
        return "page/rest";
    }


}
