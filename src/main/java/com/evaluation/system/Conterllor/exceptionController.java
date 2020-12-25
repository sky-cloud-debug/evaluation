//package com.evaluation.system.Conterllor;
//
//import org.apache.shiro.authz.UnauthorizedException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@ControllerAdvice
//public class exceptionController {
//
//    @ExceptionHandler(value = UnauthorizedException.class)//处理访问方法时权限不足问题
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("来到新写的页面");
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("msg", "上传文件超出限制!");
//        mv.setViewName("/extra/error");
//        return mv;
//    }
//}
