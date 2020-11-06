package com.evaluation.system;

import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.RoleRepository;
import com.evaluation.system.Dao.UserRepository;
import com.evaluation.system.Service.Impl.*;
import com.evaluation.system.Service.RoleService;
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
    @Autowired
    RoleService service;
    @Test
    public void contextLoads() {
        //ArrayList<ShowStu> list= (ArrayList<ShowStu>) basicRepository.ShowScoreLike("%计开发%");
        user us=new user("2018212405","123456",1);
        String s=userServicelmpl.adduser(us);
        System.out.println(s);
    }

}
