package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.UserRepository;
import com.evaluation.system.Service.userService;
import com.evaluation.system.domain.user;
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
    public String updatapassword(user user) {
        Integer permissions=user.getPermissions();
        if(permissions==1){
            user.setPermissions(0);
            userRepository.save(user);
            return "修改成功";
        }else {
            return "修改失败";
        }
    }

    @Override
    public String resetuser(user user) {
        String number=user.getNumber();
        Integer pass=Integer.parseInt(number);
        user.setPasswordId(pass);
        user.setPermissions(1);
        userRepository.save(user);
        return "重置成功";
    }

}
