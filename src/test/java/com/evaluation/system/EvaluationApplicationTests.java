package com.evaluation.system;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.UserRepository;
import com.evaluation.system.Service.Impl.BasicServicelpml;
import com.evaluation.system.Service.Impl.ScholarshipServiceImpl;
import com.evaluation.system.Service.Impl.ShowAwardslpml;
import com.evaluation.system.Service.Impl.userServicelmpl;
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
    @Test
    public void contextLoads() {
        qtScholarship name = scholarshipService.findBynumberAndbonus_name("2018212435", "社会实践");
        name.setBonus_name("完美");
        String s;
        s = scholarshipService.updata_qt(name);
        System.out.println(s);
    }

}
