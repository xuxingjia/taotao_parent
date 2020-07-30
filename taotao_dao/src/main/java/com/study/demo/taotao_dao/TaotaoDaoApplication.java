package com.study.demo.taotao_dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.study.demo.taotao_dao.mapper")
public class TaotaoDaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaotaoDaoApplication.class, args);
    }

}
