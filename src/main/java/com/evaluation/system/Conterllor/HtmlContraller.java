//package com.evaluation.system.Conterllor;
//
//import org.apache.shiro.authz.annotation.Logical;
//import org.apache.shiro.authz.annotation.RequiresRoles;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class HtmlContraller {
//
//    @RequiresRoles(value = {"student","monitor"},logical = Logical.OR)
//    @GetMapping("/scoring_totalGrade")
//    public String scoring_totalGrade(){
//        return "scoring/totalGrade";
//    }
//}
//
