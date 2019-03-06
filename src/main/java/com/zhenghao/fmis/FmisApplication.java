package com.zhenghao.fmis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhenghao.fmis.dao")
public class FmisApplication {

    public static void main(String[] args) {
        SpringApplication.run(FmisApplication.class, args);
    }

}
