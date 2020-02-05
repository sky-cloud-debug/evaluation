package com.evaluation.system.Dao;

import com.evaluation.system.domain.Password;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordReposity extends JpaRepository<Password,Integer> {


}
