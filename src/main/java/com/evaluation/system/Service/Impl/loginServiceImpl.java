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
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.validation.constraints.AssertTrue;
import java.util.Optional;

@Service
public class loginServiceImpl implements loginService {

    @Autowired
    private PasswordReposity passwordReposity;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(String number, String password) {
        user user1 =userRepository.findByNumber(number);

//        Subject subject= SecurityUtils.getSubject();
//        UsernamePasswordToken token=new UsernamePasswordToken(number,password);
//        try {
//            subject.login(token);
//            return true;
//        }catch (UnknownAccountException e){
//            return false;
//        }catch (IncorrectCredentialsException e){
//            return false;
//        }

        if(user1==null){
            return false;
        }
        //Optional的机制
        //Optional<Password>password1=passwordReposity.findById(user1.getPasswordId());
        Password password1=passwordReposity.findById(user1.getPasswordId()).orElse(null);
        if(!password1.getPassword().equals(password)){
            return false;
        }
        System.out.println(":::::"+passwordReposity.findById(user1.getPasswordId()));
        return true;
    }

}
