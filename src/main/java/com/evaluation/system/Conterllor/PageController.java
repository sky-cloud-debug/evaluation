package com.evaluation.system.Conterllor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/list")
    public String Tolist(){
        return "index";
    }
}
