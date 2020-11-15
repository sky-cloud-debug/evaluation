package com.evaluation.system.Dao;

import com.evaluation.system.domain.ExtraEntity.temporarybasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface temporarybasicRepository extends JpaRepository<temporarybasic,String> {

    public temporarybasic findByNumber(String number);

    @Modifying
    public int deleteByNumber(String number);

    public List<temporarybasic> findAll();
}
