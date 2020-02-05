package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.PasswordReposity;
import com.evaluation.system.Dao.UserRepository;
import com.evaluation.system.Service.loginService;
import com.evaluation.system.domain.Password;
import com.evaluation.system.domain.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import javax.validation.constraints.AssertTrue;
import java.util.Optional;

@Service
public class loginServiceImpl implements loginService {

    @Autowired
    PasswordReposity passwordReposity;

    @Autowired
    UserRepository userRepository;

    @Override
    public String login(String username, String password) {
        user user1 =userRepository.findByNumber(username);
        if(user1==null){
            return "没有此用户，请联系管理员注册登录！";
        }
        //Optional的机制
        //Optional<Password>password1=passwordReposity.findById(user1.getPasswordId());
        Password password1=passwordReposity.findById(user1.getPasswordId()).orElse(null);
        if(!password1.getPassword().equals(password)){
            return "密码错误";
        }
        System.out.println(":::::"+passwordReposity.findById(user1.getPasswordId()));
        return "登录成功";
    }

}
