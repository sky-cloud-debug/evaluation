package com.evaluation.system;

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

    @Test
    public void contextLoads() {

        user user=userServicelmpl.findByNumber("20171935");
        String s = userServicelmpl.updatapassword(user);
        System.out.println(s);
    }

}
