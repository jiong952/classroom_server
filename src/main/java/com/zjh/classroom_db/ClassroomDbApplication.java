package com.zjh.classroom_db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zjh.classroom_db.mapper")
public class ClassroomDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassroomDbApplication.class, args);
    }

}
