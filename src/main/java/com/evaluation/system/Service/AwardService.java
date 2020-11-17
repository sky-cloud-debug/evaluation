package com.evaluation.system.Service;

import com.evaluation.system.domain.Award;

import java.util.List;

public interface AwardService {

    public List<Award> findByNumber(String number);
}
