package com.evaluation.system.Service;

import com.evaluation.system.domain.ShowStu;
import com.evaluation.system.domain.basic;

import java.util.List;

public interface BasicService {

    public List<ShowStu> ShowScore(String classmajor);

    public basic findbynumber(String number);

    public String updatabasic(basic ba,String number);
}
