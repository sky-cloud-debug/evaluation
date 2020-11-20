package com.evaluation.system;

import com.evaluation.system.Dao.AwardTempRepository;
import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.QualityRepository;
import com.evaluation.system.Service.Impl.*;
import com.evaluation.system.Service.QuailtyService;
//import com.evaluation.system.Service.RoleService;
import com.evaluation.system.Service.ScholarshipService;
import com.evaluation.system.domain.*;
import com.evaluation.system.domain.ExtraEntity.exportquality;
import com.evaluation.system.util.DateUtils;
import com.evaluation.system.util.ExcelUtils.EntityExcelUtil;
import com.evaluation.system.util.ExcelUtils.Excellmpl.qualityExceImpl;
import com.evaluation.system.util.RouterUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@SpringBootTest
class EvaluationApplicationTests {

    @Autowired
    ScholarshipService scholarshipService;


    @Test
    public void contextLoads() {
        //int state,String number,String year
        int u = scholarshipService.updateYxScholarshipState(2, "2018212405", "第三学年","我是你爸爸");
        System.out.println(u);
    }

}
