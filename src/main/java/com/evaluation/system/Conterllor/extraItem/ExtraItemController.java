package com.evaluation.system.Conterllor.extraItem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/extra")
public class ExtraItemController {

    @GetMapping("/add")
    public String add(){
        return "extra/add";
    }


    @GetMapping("/test")
    public String test(){
        return "extra/test/add";
    }
}
