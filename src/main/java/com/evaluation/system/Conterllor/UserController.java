package com.evaluation.system.Conterllor;

import com.evaluation.system.Dao.UserRepository;
import com.evaluation.system.Service.Impl.userServicelmpl;
import com.evaluation.system.domain.ExtraEntity.ChangePwd;
import com.evaluation.system.domain.user;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    userServicelmpl userservicelmpl;


    @Autowired
    UserRepository userRepository;

    @GetMapping("/tofindpower")
    @ResponseBody
    public String tofindpower(){
        System.out.println("来到了这里获取群贤");
        Subject subject= SecurityUtils.getSubject();
        if(subject.hasRole("monitor")){
            return "monitor";
        }else if(subject.hasRole("admin")){
            return "admin";
        }else {
            return "student";
        }
    }
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
    public  String findtoEdituser(@RequestBody(required=false)ChangePwd pwd,HttpServletRequest request){
        String oldpassword=pwd.getOldpassword();
        String newpassword1=pwd.getNewpassword1();
        String usernumber=(String)request.getSession().getAttribute("number");
        Md5Hash md5Hash=new Md5Hash(oldpassword,usernumber,1024);
        oldpassword=md5Hash.toHex();
        user us=finduserbynumber(usernumber);
        if(!us.getPassword().equals(oldpassword)){
            return "旧密码错误！";
        }
        Integer permissions=us.getPermissions();
        if(permissions==1){
            us.setPermissions(0);
            Md5Hash md5Hash1=new Md5Hash(newpassword1,usernumber,1024);
            newpassword1=md5Hash1.toHex();
            us.setPassword(newpassword1);
            userRepository.save(us);
            return "修改成功！";
        }else {
            return "无权限修改密码！";
        }
    }

    //管理员重置密码
    @RequestMapping(value="/UserController/resetpassword",method = RequestMethod.POST)
    @ResponseBody
   // @PutMapping("/resetpassword")
    public String resetpassword(@RequestBody(required=false)HttpServletRequest request){
        String number=request.getParameter("number");
        System.out.println(number);
        String s = userservicelmpl.resetuser(number);
        return s;
    }
}
