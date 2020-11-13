package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.QualityRepository;
import com.evaluation.system.Service.QuailtyService;
import com.evaluation.system.domain.quality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class Qualitylmpl implements QuailtyService {

    @Autowired
    QualityRepository qualityRepository;

    @Transactional
    @Override
    public String updateQuality(quality a) {
        try {
            qualityRepository.save(a);
            return "更新成功！";
        }catch (Exception e){
            e.printStackTrace();
            return "更新失败!";
        }

    }

    @Override
    public quality findbynumber(String number) {
        return qualityRepository.findByNumber(number);
    }
    @Transactional
    @Override
    public boolean updateMoralbyNumber(Double moral, String number) {
        int n=qualityRepository.updateMoralbyNumber(moral,number);
        if(n==1){
            return true;
        }else {
            return false;
        }
    }
    @Transactional
    @Override
    public boolean updateHeartbyNumber(Double heart, String number) {
        int n=qualityRepository.updateHeartbyNumber(heart,number);
        if(n==1){
            return true;
        }else {
            return false;
        }
    }
    @Transactional
    @Override
    public boolean updateTechnologybyNumber(Double technology, String number) {
        int n=qualityRepository.updateTechnologybyNumber(technology,number);
        if(n==1){
            return true;
        }else {
            return false;
        }
    }
}
