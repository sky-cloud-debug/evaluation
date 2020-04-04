package com.evaluation.system.Conterllor;

import com.evaluation.system.Dao.PasswordReposity;
import com.evaluation.system.Service.Impl.userServicelmpl;
import com.evaluation.system.domain.Password;
import com.evaluation.system.domain.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    userServicelmpl userservicelmpl;

    @Autowired
    PasswordReposity passwordReposity;

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

    @PutMapping("/finduser")
    public  String findtoEdituser(HttpServletRequest request, Session session){
        String oldpassword=request.getParameter("oldpassword");
        String newpassword1=request.getParameter("newpassword1");
        String newpassword2=request.getParameter("newpassword2");

        String usernumber=(String)request.getSession().getAttribute("number");
        user us=finduserbynumber(usernumber);
        Password password1=passwordReposity.findById(us.getPasswordId()).orElse(null);
        if(!password1.getPassword().equals(oldpassword)){
            return "旧密码错误";
        }
        if(!newpassword1.equals(newpassword2)){
            return "新密码输入不同";
        }

        Integer permissions=us.getPermissions();
        if(permissions==1){
            us.setPermissions(0);
            password1.setPassword(newpassword1);
            passwordReposity.save(password1);
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    //管理员重置密码
    @PutMapping("/resetpassword")
    public String resetpassword(HttpServletRequest request){
        String number=request.getParameter("number");
        String s = userservicelmpl.resetuser(number);
        return s;
    }
}
