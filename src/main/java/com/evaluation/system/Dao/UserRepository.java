package com.evaluation.system.Dao;

import com.evaluation.system.domain.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<user,Integer> {

    public user findByNumber(String number);

}
