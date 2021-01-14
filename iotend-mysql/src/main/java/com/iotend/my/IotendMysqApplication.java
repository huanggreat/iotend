package com.iotend.my;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@SpringBootApplication
@EnableAsync
public class IotendMysqApplication {
    public static void main(String[] args) {
        SpringApplication.run(IotendMysqApplication.class,args);
    }
}
