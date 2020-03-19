package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.temporarybasicRepository;
import com.evaluation.system.Service.TembasicService;
import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.temporarybasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TempbasicServicelmpl implements TembasicService {

    @Autowired
    temporarybasicRepository Repository;

    @Override
    public String addtemporarybasic(temporarybasic te) {
        Repository.save(te);
        return "添加成功";
    }

    @Override
    public temporarybasic findtemporarybasic(String number) {
        return Repository.findByNumber(number);
    }

    @Override
    public String deleteByNumber(String number) {
        Repository.deleteByNumber(number);
        return "删除成功";
    }

    @Override
    public List<temporarybasic> findall() {
        return Repository.findAll();
    }
}
