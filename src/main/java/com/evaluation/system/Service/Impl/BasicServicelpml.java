package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Service.BasicService;
import com.evaluation.system.domain.ShowStu;
import com.evaluation.system.domain.basic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BasicServicelpml implements BasicService {

    @Autowired
    private BasicRepository basicRepository;

    @Override
    public List<ShowStu> ShowScore() {
        return basicRepository.ShowScore();
    }

    @Override
    public basic findbynumber(String number) {
        basic ba = basicRepository.findByNumberContains(number);
        return ba;
    }

    @Override
    public String updatabasic(basic ba,String number) {
        String num=ba.getNumber();
        if(!num.equals(number)){
            basicRepository.removeByNumber(number);
        }
        basicRepository.save(ba);
        return "修改成功";
    }
}
