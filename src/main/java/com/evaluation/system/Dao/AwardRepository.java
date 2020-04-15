package com.evaluation.system.Dao;

import com.evaluation.system.domain.Award;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardRepository extends JpaRepository<Award, String> {
}
