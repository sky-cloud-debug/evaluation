package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.QtScholarshipRepository;
import com.evaluation.system.Dao.yxScholarshipRepository;
import com.evaluation.system.Service.ShowAwards;
import com.evaluation.system.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowAwardslpml implements ShowAwards {

    @Autowired
    BasicRepository basicRepository;

    @Autowired
    QtScholarshipRepository qtScholarshipRepository;

    @Autowired
    yxScholarshipRepository yxScholarshipRepository;

    @Override
    public yxScholarship Showyxawards(String num) {
        return yxScholarshipRepository.getOne(num);
    }

    @Override
    public qtScholarship Showqtawards(String num) {
        return qtScholarshipRepository.getOne(num);
    }

    @Override
    public basic ShowStudent(String num) {
        return basicRepository.getOne(num);
    }

    @Override
    public List<AllqtAwards> findAllqtAwards() {
        return basicRepository.FindqtAward();
    }

    @Override
    public List<AllxyAwards> findAllyxAwards() {
        return basicRepository.FindyxAward();
    }
}
