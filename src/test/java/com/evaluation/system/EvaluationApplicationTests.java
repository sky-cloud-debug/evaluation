package com.evaluation.system;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.UserRepository;
import com.evaluation.system.Service.Impl.*;
import com.evaluation.system.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootTest
class EvaluationApplicationTests {

    @Autowired
    ShowAwardslpml showAwardslpml;

    @Autowired
    BasicServicelpml basicServicelpml;
    @Autowired
    ScholarshipServiceImpl scholarshipService;

    @Autowired
    userServicelmpl userServicelmpl;

    @Autowired
    BasicRepository basicRepository;

    @Autowired
    TempbasicServicelmpl tempbasicServicelmpl;
    @Test
    public void contextLoads() {
//        List<qtScholarship> personAllByNumber = showAwardslpml.findPersonAllByNumber("2018212435");
//        String number="2018212435";
//        for (int i=0;i<personAllByNumber.size();i++){
//            System.out.println(personAllByNumber.get(i));
//        }
        yxScholarship showyxawards = showAwardslpml.findyxByNumber("2018212435");
        System.out.println(showyxawards);
    }

}
