package com.evaluation.system.Conterllor;

import com.evaluation.system.Service.Impl.userServicelmpl;
import com.evaluation.system.domain.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    userServicelmpl userservicelmpl;

    //查找用户
    public user finduserbynumber(String number){
        user us=null;
        us= userservicelmpl.findByNumber(number);
        if(us!=null){
            return us;
        }else {
            return null;
        }
    }

    //感觉这个地方可以作为验证登录的时候用到顺手写了
    @GetMapping("/finduser")
    public boolean findtoshow(Model model, HttpServletRequest request){
        String number=request.getParameter("number");
        Integer password=request.getIntHeader("password");
        user us=finduserbynumber(number);
        if(us!=null){
            if(us.getNumber()==number&&us.getPasswordId()==password){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }

    //下面是修改user信息
    @PutMapping("/finduser")
    public  String findtoEdituser(Model model, HttpServletRequest request){
        String number=request.getParameter("number");
        user us=finduserbynumber(number);
        if(us!=null){
            model.addAttribute("Mes",us);
        }else {
            model.addAttribute("Mes","信息查找失败");
        }
        return "usering/modify";//等待新建(信息回显)
    }
    //修改信息的页面
    @PutMapping("modify")
    public String updatauser(user us,Model model){
        String s = userservicelmpl.updatapassword(us);
        if(s.equals("修改成功")){
            return "";//成功页面
        }else {
            return "";//失败页面
        }
    }

    //管理员重置密码
    @PutMapping("/resetpassword")
    public String resetpassword(Model model,HttpServletRequest request){
        String number=request.getParameter("number");
        user user = finduserbynumber(number);
        if(user!=null){
            String msg=userservicelmpl.resetuser(user);
            if(msg.equals("重置成功")){
                return "";//成功页面
            }else {
                return "";//失败页面
            }
        }else {
            return "";//失败页面
        }
    }

}
