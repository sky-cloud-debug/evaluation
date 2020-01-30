package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.QtScholarshipRepository;
import com.evaluation.system.Dao.yxScholarshipRepository;
import com.evaluation.system.Service.ScholarshipService;
import com.evaluation.system.domain.qtScholarship;
import com.evaluation.system.domain.yxScholarship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScholarshipServiceImpl implements ScholarshipService {

    @Autowired
    yxScholarshipRepository yxScholarshipRepository;

    @Autowired
    QtScholarshipRepository qtScholarshipRepository;

    @Override
    public String add_yx(String level,String card) {

        yxScholarship yxScholarship=new yxScholarship();
        if(card.equals(null)||card.equals("")){
            return "卡号错误";
        }
        int Card=Integer.parseInt(card);

        yxScholarship.setNumber("1234567");     //没有获取主键
        yxScholarship.setCardNumber(Card);
        yxScholarship.setScholarshipLevel(level);
        yxScholarship.setReason("--");
        yxScholarship.setState("--");
        yxScholarshipRepository.save(yxScholarship);
        return "申请成功，等待审批";
    }

    @Override
    public String add_qt(String bonusName, String card) {

        if(card.equals(null)||card.equals("")){
            return "卡号错误";
        }
        int card1= Integer.parseInt(card);
        qtScholarship qtScholarship=new qtScholarship("1234",bonusName,card1,"--","--");
        qtScholarshipRepository.save(qtScholarship);
        return "申请成功，等待审批";
    }
}
