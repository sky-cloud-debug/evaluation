package com.evaluation.system;

import com.evaluation.system.Dao.AwardTempRepository;
import com.evaluation.system.Dao.BasicRepository;
import com.evaluation.system.Dao.QualityRepository;
import com.evaluation.system.Service.Impl.*;
import com.evaluation.system.Service.QuailtyService;
//import com.evaluation.system.Service.RoleService;
import com.evaluation.system.domain.*;
import com.evaluation.system.util.DateUtils;
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
//    RoleService service;

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
//        ArrayList<AwardTemp> byNumber = awardTempRepository.findByNumber("2018212405");
//        for (AwardTemp a : byNumber) {
//            System.out.println(a);
//        }
    }

    @Value("${supportPath}")
    public String supportPath;

    @Test
    public void test01() {
        Date updateTime = DateUtils.getCurrentDate();
        String uuid = RouterUtils.generateRandomString();
        String router = "2018212602" + '-' + uuid;
        AwardTemp awardTemp = new AwardTemp("2018212602", "江宇轩", "科技人文素质", "国家级一等奖", "山东省物理竞赛", 5, 0, "", "计算机18-4", router, updateTime);

        String dirPath = supportPath + awardTemp.getClassMajor();
        File dir = new File(dirPath);
        if (!dir.exists() && !dir.isDirectory()) {
            System.out.println("班级文件夹不存在，创建！");
            dir.mkdir();
        }
        router = supportPath + awardTemp.getClassMajor() + "/" + awardTemp.getRouter() + ".jpg";
        awardTemp.setRouter(router);
        awardTempRepository.save(awardTemp);
        System.out.println("上传成功");

    }

}
