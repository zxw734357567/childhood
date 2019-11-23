package com.zxw.childhood.jojoauth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class JojoAuthApplication {
    private static final Logger logger = LoggerFactory.getLogger(JojoAuthApplication.class);
    public static void main(String[] args) {
        logger.debug("auth start up!");
        SpringApplication.run(JojoAuthApplication.class, args);
    }

}
