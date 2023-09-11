package com.Qiu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.Qiu.mapper")
public class QiuBlogApplication {
    public static void main(String[] args){
        SpringApplication.run(QiuBlogApplication.class,args);
    }
}
