package com.iotend.gql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class IotendGraphqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(IotendGraphqlApplication.class,args);
    }
}
