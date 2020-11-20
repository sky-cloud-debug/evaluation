package com.evaluation.system;

import com.evaluation.system.Service.ScholarshipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

//import com.evaluation.system.Service.RoleService;

@RestController
@SpringBootTest
class EvaluationApplicationTests {

    @Autowired
    ScholarshipService scholarshipService;


    @Test
    public void contextLoads() {
        //int state,String number,String year
        int u = scholarshipService.updateYxScholarshipState(2, "2018212405", "第三学年","");
        System.out.println(u);
    }

}
