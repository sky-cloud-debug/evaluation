package com.evaluation.system.Service;

import com.evaluation.system.domain.AwardTemp;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface ExamineService {


    public ArrayList<AwardTemp> getAwardTempInfo(String classMajor, String judge);

    public ArrayList<AwardTemp> getAwardTempRejectInfo(String judge);

    public String insertAwardTemp(AwardTemp awardTemp, MultipartFile file);

    public String judgeMaterials(AwardTemp awardTemp);

    public ArrayList<AwardTemp> getJudgeResult(String number);

}
