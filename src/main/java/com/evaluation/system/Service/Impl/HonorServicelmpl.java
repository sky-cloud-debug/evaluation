package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.HonorRepository;
import com.evaluation.system.Service.HonorService;
import com.evaluation.system.domain.ExtraEntity.VerifyHonor;
import com.evaluation.system.domain.honor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class HonorServicelmpl implements HonorService {

    @Autowired
    HonorRepository honorRepository;

    @Override
    public List<honor> findByNumber(String number) {
        return honorRepository.findByNumber(number);
    }

    @Override
    public boolean addHonor(honor honor) {
        honor save = honorRepository.save(honor);
        return save!=null;
    }

    @Override
    public int deleteHonor(String number, String honor, String year,int state) {
        return honorRepository.deleteByNumberAndStudentHonorAndYearAndState(number,honor,year,state);
    }

    @Override
    public List<VerifyHonor> findHonorByClassMajorAndYear(String classmajor,int state) {
        return honorRepository.findByClssMajorAndYearAndState(classmajor,state);
    }

    @Override
    public int updateStateByNumAndHorAndYe(String number, String honor, String year, int state) {
        return honorRepository.updateStateByNumAndHorAndYe(number,honor,year,state);
    }
}
