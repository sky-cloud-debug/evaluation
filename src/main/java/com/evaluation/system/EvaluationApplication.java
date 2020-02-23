package com.evaluation.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EvaluationApplication {
    private Logger logger= LoggerFactory.getLogger(EvaluationApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(EvaluationApplication.class, args);
    }

}
