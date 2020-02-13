package com.evaluation.system.Service;

import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.testGroup;

import java.util.ArrayList;

public interface ScoreService {

    public ArrayList<basic> getClassInfo(String classMajor);

    public ArrayList<testGroup> getTestInfo(String classMajor);

}
