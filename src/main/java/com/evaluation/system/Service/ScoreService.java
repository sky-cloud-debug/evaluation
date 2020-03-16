package com.evaluation.system.Service;

import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.testGroup;

import java.io.IOException;
import java.util.ArrayList;

public interface ScoreService {

    public ArrayList<basic> getClassInfo(String classMajor);

    public ArrayList<testGroup> getTestInfo(String classMajor);

    public String setClassMember(String classMajor, ArrayList<String> namelist) throws IOException;

    public String setTestMember(String classMajor, ArrayList<String> scoreList);

    public String submitScore(String classMajor, String name, String type, ArrayList<Integer> scoreList);

    public String Calculate(String classMajor);

}
