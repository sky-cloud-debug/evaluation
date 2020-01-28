package com.evaluation.system.Dao;

import com.evaluation.system.domain.user;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<user,Integer> {
    public user findByPasswordId(int id);
}
