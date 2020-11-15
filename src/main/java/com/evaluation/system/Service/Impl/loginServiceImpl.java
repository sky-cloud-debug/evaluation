package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.UserRepository;
import com.evaluation.system.Service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;


@Service
public class loginServiceImpl implements loginService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean login(String number, String password, RedirectAttributesModelMap model) {

       return true;
    }

}
