package com.evaluation.system;

import com.evaluation.system.Dao.AwardTempRepository;
import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.QtScholarshipRepository;
import com.evaluation.system.Dao.QualityRepository;
import com.evaluation.system.Service.*;
import com.evaluation.system.Service.Impl.*;
import com.evaluation.system.domain.ExtraEntity.AllqtAwards;
import com.evaluation.system.domain.ExtraEntity.AllxyAwards;
import com.evaluation.system.domain.ExtraEntity.exportquality;
import com.evaluation.system.domain.basic;
import com.evaluation.system.domain.qtScholarship;
import com.evaluation.system.domain.user;
import com.evaluation.system.util.ExcelUtils.EntityExcelUtil;
import com.evaluation.system.util.ExcelUtils.Excellmpl.basicExceImpl;
import com.evaluation.system.util.ExcelUtils.Excellmpl.qualityExceImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootTest
class EvaluationApplicationTests {

    @Autowired
    QtScholarshipRepository qtScholarshipRepository;

    @Autowired
    ScholarshipService scholarshipService;
    @Test
    public void contextLoads() {

        List<AllxyAwards> list=scholarshipService.findyxByYearAndClassMajor("第一学年","计算机18-4");
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }

}
