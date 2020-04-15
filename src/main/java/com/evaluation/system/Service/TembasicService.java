package com.evaluation.system.Service;


import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.temporarybasic;

import java.util.List;

public interface TembasicService {

    public String addtemporarybasic(temporarybasic tem);

    public temporarybasic findtemporarybasic(String number);

    public String deleteByNumber(String number);

    public List<temporarybasic> findall();
}
