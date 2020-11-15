package com.evaluation.system.Service;


import com.evaluation.system.domain.ExtraEntity.temporarybasic;

import java.util.List;

public interface TembasicService {

    public boolean addtemporarybasic(temporarybasic tem);

    public temporarybasic findtemporarybasic(String number);

    public boolean deleteByNumber(String number);

    public List<temporarybasic> findall();
}
