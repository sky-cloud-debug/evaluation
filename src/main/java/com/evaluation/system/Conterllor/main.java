package com.evaluation.system.Conterllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class main {

    @GetMapping("/add")
    public String add(){
        return "table/add";
    }
}