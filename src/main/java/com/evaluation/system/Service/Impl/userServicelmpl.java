package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.UserRepository;
import com.evaluation.system.Service.userService;
import com.evaluation.system.domain.user;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServicelmpl implements userService {

    @Autowired
    UserRepository userRepository;


    @Override
    public user findByNumber(String number) {
        return userRepository.findByNumber(number);
    }

    @Override
    public String resetuser(String number) {
        user us = userRepository.findByNumber(number);
        if(us!=null){
            Md5Hash md5Hash=new Md5Hash(number,number,1024);
            String newpass=md5Hash.toHex();
            us.setPermissions(1);
            us.setPassword(newpass);
            userRepository.save(us);
            return "重置成功";
        }else {
            return "账号错误";
        }
    }

    @Override
    public boolean adduser(user us) {
        Md5Hash md5Hash = new Md5Hash(us.getPassword(), us.getNumber(), 1024);
        us.setPassword(md5Hash.toHex());
        user save = userRepository.save(us);
        return save != null;
    }
}
