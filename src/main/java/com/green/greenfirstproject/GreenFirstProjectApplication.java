package com.green.greenfirstproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GreenFirstProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenFirstProjectApplication.class, args);
    }

}
