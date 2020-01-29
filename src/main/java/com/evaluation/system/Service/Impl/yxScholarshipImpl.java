package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.yxScholarshipRepository;
import com.evaluation.system.Service.yxScholarshipService;
import com.evaluation.system.domain.yxScholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class yxScholarshipImpl implements yxScholarshipService {

    @Autowired
    yxScholarshipRepository yxScholarshipRepository;

    @Override
    public String add_yx(String level,String card) {
        yxScholarship yxScholarship=new yxScholarship();
        if(level==null||level.equals("")){
            return "填写奖学金错误";
        }
        if(card.equals(null)||card.equals("")){
            return "卡号错误";
        }
        int Card=Integer.parseInt(card);
        yxScholarship.setCardNumber(Card);
        yxScholarship.setScholarshipLevel(level);
        yxScholarship.setReason("--");
        yxScholarship.setState("--");
        yxScholarshipRepository.save(yxScholarship);
        return "申请成功";
    }

    @Override
    public String add_qt(String name,String card){

        return null;
    }
}
