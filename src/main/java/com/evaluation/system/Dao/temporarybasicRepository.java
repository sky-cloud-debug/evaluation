package com.evaluation.system.Dao;

import com.evaluation.system.domain.temporarybasic;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface temporarybasicRepository extends JpaRepository<temporarybasic,String> {

    public temporarybasic findByNumber(String number);

    @Transactional
    public String deleteByNumber(String number);

    public List<temporarybasic> findAll();
}
