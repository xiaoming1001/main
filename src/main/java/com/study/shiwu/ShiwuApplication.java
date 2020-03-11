package com.study.shiwu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ShiwuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiwuApplication.class, args);
    }

}
