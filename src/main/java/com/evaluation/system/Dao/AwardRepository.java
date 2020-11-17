package com.evaluation.system.Dao;

import com.evaluation.system.domain.Award;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * author:江宇轩
 */
public interface AwardRepository extends JpaRepository<Award, String> {

    public List<Award> findByNumber(String number);
}
