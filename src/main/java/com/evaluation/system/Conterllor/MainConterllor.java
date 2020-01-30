package com.evaluation.system.Conterllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainConterllor {

    @GetMapping("/add")
    public String add(){
        return "table/add";
    }

    @GetMapping("/form")
    public String form(){return "page/form";}
}
