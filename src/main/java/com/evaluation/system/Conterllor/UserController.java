package com.evaluation.system.Conterllor;

import com.evaluation.system.Dao.PasswordReposity;
import com.evaluation.system.Service.Impl.userServicelmpl;
import com.evaluation.system.domain.ChangePwd;
import com.evaluation.system.domain.Password;
import com.evaluation.system.domain.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value="/UserController/findtoEdituser",method = RequestMethod.POST)
    @ResponseBody
   // @PutMapping("/finduser")
    public  String findtoEdituser(@RequestBody(required=false)ChangePwd pwd,HttpServletRequest request){

//        System.out.println(pwd.getOldpassword());
//        System.out.println(pwd.getNewpassword1());
//        System.out.println(pwd.getNewpassword2());
//        return null;
        String oldpassword=pwd.getOldpassword();
        String newpassword1=pwd.getNewpassword1();
        String newpassword2=pwd.getNewpassword2();

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
    @RequestMapping(value="/UserController/resetpassword",method = RequestMethod.POST)
    @ResponseBody
   // @PutMapping("/resetpassword")
    public String resetpassword(@RequestBody(required=false)String number){
        //System.out.print(number);
        String s = userservicelmpl.resetuser(number);
        return s;
    }
}
