package com.evaluation.system.Dao;

import com.evaluation.system.domain.quality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualityRepository extends JpaRepository<quality,String> {
    public quality findBywisdom(double asd);

    public quality findByClassMajor(String major);


}