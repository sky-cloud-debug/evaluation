package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.PasswordReposity;
import com.evaluation.system.Dao.UserRepository;
import com.evaluation.system.Service.userService;
import com.evaluation.system.domain.Password;
import com.evaluation.system.domain.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userServicelmpl implements userService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordReposity passwordReposity;

    @Override
    public user findByNumber(String number) {
        return userRepository.findByNumber(number);
    }


    @Override
    public String resetuser(String number) {

        user us = userRepository.findByNumber(number);
        Password password1=passwordReposity.findById(us.getPasswordId()).orElse(null);
        password1.setPassword(number);
        passwordReposity.save(password1);
        us.setPermissions(1);
        userRepository.save(us);
        return "重置成功";
    }

}
