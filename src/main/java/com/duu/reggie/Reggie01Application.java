package com.duu.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class Reggie01Application {

    public static void main(String[] args) {
        SpringApplication.run(Reggie01Application.class, args);
        log.info("启动成功");
    }

}
