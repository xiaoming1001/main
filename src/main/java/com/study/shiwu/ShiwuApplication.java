package com.study.shiwu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })  //配置多数据源一定加除去原本默认的配置**
@EnableTransactionManagement
public class ShiwuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiwuApplication.class, args);
    }

}
