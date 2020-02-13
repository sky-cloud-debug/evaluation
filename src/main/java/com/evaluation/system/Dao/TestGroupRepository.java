package com.evaluation.system.Dao;

import com.evaluation.system.domain.testGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TestGroupRepository extends JpaRepository<testGroup, String> {

    public ArrayList<testGroup> findByClassMajor(String classMajor);
}
