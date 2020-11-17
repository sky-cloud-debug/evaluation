package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.AwardRepository;
import com.evaluation.system.Service.AwardService;
import com.evaluation.system.domain.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class AwardServicelmpl implements AwardService {

    @Autowired
    AwardRepository awardRepository;

    @Override
    public List<Award> findByNumber(String number) {
        return awardRepository.findByNumber(number);
    }
}
