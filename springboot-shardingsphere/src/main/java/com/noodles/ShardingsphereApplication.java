package com.noodles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ShardingsphereApplication {

    private static final Logger logger = LoggerFactory.getLogger(ShardingsphereApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ShardingsphereApplication.class, args);
        logger.info("分表案例启动");
    }
}
