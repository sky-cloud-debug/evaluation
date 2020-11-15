package com.evaluation.system.Service.Impl;

import com.evaluation.system.Dao.temporarybasicRepository;
import com.evaluation.system.Service.TembasicService;
import com.evaluation.system.domain.ExtraEntity.temporarybasic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class TempbasicServicelmpl implements TembasicService {

    @Autowired
    temporarybasicRepository Repository;

    @Override
    public boolean addtemporarybasic(temporarybasic te) {
        temporarybasic save = Repository.save(te);
        return save!=null;
    }

    @Override
    public temporarybasic findtemporarybasic(String number) {
        return Repository.findByNumber(number);
    }

    @Override
    public boolean deleteByNumber(String number) {
        int i= Repository.deleteByNumber(number);
        return i==1;
    }

    @Override
    public List<temporarybasic> findall() {
        return Repository.findAll();
    }
}
