package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Service.BasicService;
import com.evaluation.system.domain.ShowStu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BasicServicelpml implements BasicService {

    @Autowired
    private BasicRepository basicRepository;

    @Override
    public List<ShowStu> ShowScore() {
        return basicRepository.ShowScore();
    }
}
