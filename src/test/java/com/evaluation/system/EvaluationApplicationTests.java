package com.evaluation.system;

import com.evaluation.system.Dao.AwardTempRepository;
import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.QualityRepository;
import com.evaluation.system.Service.Impl.*;
import com.evaluation.system.Service.QuailtyService;
//import com.evaluation.system.Service.RoleService;
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

//    @Autowired
//    RoleService senarvice;

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
        String path="C:\\Users\\Lenovo\\Desktop\\student.xlsx";
        EntityExcelUtil<exportquality> excelUtil=new qualityExceImpl();
        List<exportquality> all=quailtyService.exportquality();
        boolean i=excelUtil.exportExcel(all,path,"sheet1");

        System.out.println(i);
    }

}
