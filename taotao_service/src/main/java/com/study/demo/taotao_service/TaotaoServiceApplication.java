package com.study.demo.taotao_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.study.demo.taotao_dao.mapper")
@SpringBootApplication(scanBasePackages = "com.study.demo")
public class TaotaoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaotaoServiceApplication.class, args);
    }
}