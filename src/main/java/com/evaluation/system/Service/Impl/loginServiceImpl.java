package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.PasswordReposity;
import com.evaluation.system.Dao.UserRepository;
import com.evaluation.system.Service.loginService;
import com.evaluation.system.domain.Password;
import com.evaluation.system.domain.user;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;


@Service
public class loginServiceImpl implements loginService {

    @Autowired
    private PasswordReposity passwordReposity;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(String number, String password, RedirectAttributesModelMap model) {
       /* Subject subject= SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(number,password));
        }catch (UnknownAccountException e){
            e.printStackTrace();
            model.addFlashAttribute("mes","用户名错误！");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            model.addFlashAttribute("mes","密码错误！");
        }
        if(user1==null){
            return false;
        }
        Password password1=passwordReposity.findById(user1.getPasswordId()).orElse(null);
        if(!password1.getPassword().equals(password)){
            return false;
        }
        System.out.println(":::::"+passwordReposity.findById(user1.getPasswordId()));
        return true;*/
       return true;
    }

}
