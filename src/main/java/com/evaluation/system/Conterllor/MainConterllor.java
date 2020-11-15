package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.BasicService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/main")
public class MainConterllor {

    @Autowired
    loginService loginService;
    @Autowired
    BasicService basicService;

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
            basic b = basicService.findByNumber(number);
            String s=b.getClassMajor();
            String[] s1=s.split("\\d");
            int i=s1[0].length();
            String classMajorLike = null;
            classMajorLike=s.substring(0,i);
            session.setAttribute("classMajor",s);
            session.setAttribute("number",number);
            session.setAttribute("name",b.getName());
            session.setAttribute("duty",b.getDuty());
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

    @GetMapping("/rest")
    public String reset(){
        return "page/rest";
    }


}
