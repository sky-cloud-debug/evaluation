package com.evaluation.system.Conterllor.extraItem;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/extra")
public class ExtraItemController {

    @GetMapping("/add")
    public String add(){
        return "extra/add";
    }


    @GetMapping("/addItem")
    public String addItem(Model model, HttpServletRequest request){
        Map<String, String> map = new HashMap<String, String>();

        System.out.println(request.getParameter("type1"));
        System.out.println(request.getParameter("type2"));
        System.out.println(request.getParameter("addItemName"));
        System.out.println(request.getParameter("level"));
        System.out.println(request.getParameter("file"));

        System.out.println("success");

        // 转化成ExtraItem写入数据库

        boolean addItemIfSuccess = true;
        if (addItemIfSuccess){
            return "extra/success";
        }else {
            return "extra/error";
        }

    }
}
