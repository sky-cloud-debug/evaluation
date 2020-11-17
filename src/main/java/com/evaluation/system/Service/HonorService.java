package com.evaluation.system.Service;

import com.evaluation.system.domain.ExtraEntity.VerifyHonor;
import com.evaluation.system.domain.honor;

import java.util.List;

public interface HonorService {

    public List<honor> findByNumber(String  number);
    public boolean addHonor(honor honor);
    public int deleteHonor(String number,String honor,String year,int state);
    public List<VerifyHonor> findHonorByClassMajorAndYear(String classmajor, String year,int state);
    public int updateStateByNumAndHorAndYe(String number,String honor,String year,int state);
}
