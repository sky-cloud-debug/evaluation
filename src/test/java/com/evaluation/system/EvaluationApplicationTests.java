package com.evaluation.system;

import com.evaluation.system.Service.Impl.BasicServicelpml;
import com.evaluation.system.Service.Impl.ShowAwardslpml;
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

    @Test
    public void contextLoads() {
        List<AllxyAwards> allqtAwards = showAwardslpml.findAllyxAwards();
        for(int i=0;i<allqtAwards.size();i++){
            System.out.println(allqtAwards.get(i));
        }
    }

}
