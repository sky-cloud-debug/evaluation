package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/main")
public class MainConterllor {

    @Autowired
    loginService loginService;

    @GetMapping("index")
    public String login(){return "login/Login";}

    @GetMapping("/add")
    public String add(){
        return "table/add";
    }

    @GetMapping("/form")
    public String form(){return "page/form";}

    @PostMapping("/Login")
    public String Login(Model model,HttpServletRequest request){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(username+password);
        String str=loginService.login(username,password);
        if(!str.equals("登录成功")){
            model.addAttribute("msg","用户名或密码错误");
            return "login/Login";
        }
        return "index";
    }

}
