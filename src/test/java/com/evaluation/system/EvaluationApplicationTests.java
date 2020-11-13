package com.evaluation.system;

import com.evaluation.system.Dao.AwardTempRepository;
import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.QualityRepository;
import com.evaluation.system.Service.Impl.*;
import com.evaluation.system.Service.QuailtyService;
import com.evaluation.system.Service.RoleService;
import com.evaluation.system.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@SpringBootTest
class EvaluationApplicationTests {

    @Autowired
    ShowAwardslpml showAwardslpml;

    @Autowired
    BasicServicelmpl basicServicelmpl;
    @Autowired
    ScholarshipServiceImpl scholarshipService;

    @Autowired
    userServicelmpl userServicelmpl;

    @Autowired
    BasicRepository basicRepository;

    @Autowired
    TempbasicServicelmpl tempbasicServicelmpl;

    @Autowired
    RoleService service;

    @Autowired
    ExamineServiceImpl examineService;

    @Autowired
    QuailtyService qualityService;

    @Autowired
    QuailtyService quailtyService;
    @Autowired
    AwardTempRepository awardTempRepository;
    @Test
    public void contextLoads() {
        ArrayList<AwardTemp> byNumber = awardTempRepository.findByNumber("2018212405");
        for (AwardTemp a:byNumber) {
            System.out.println(a);
        }
    }

}
