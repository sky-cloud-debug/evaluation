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

import java.util.ArrayList;
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
        //ArrayList<ShowStu> list= (ArrayList<ShowStu>) basicRepository.ShowScoreLike("%计开发%");
        basic b=basicServicelpml.findbynumber("2018212459");
            System.out.println(b);
    }

}
