package com.zhurylo.testtaskkameleoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.zhurylo.testtaskkameleoon.entity")
@EnableJpaRepositories(basePackages = "com.zhurylo.testtaskkameleoon.repository")
@ComponentScan({
        "com.zhurylo.testtaskkameleoon.service",
        "com.zhurylo.testtaskkameleoon.controller"
})
public class TestTaskKameleoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskKameleoonApplication.class, args);
    }

}
