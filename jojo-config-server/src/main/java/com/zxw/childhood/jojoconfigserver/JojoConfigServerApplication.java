package com.zxw.childhood.jojoconfigserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer //配置中心的服务
public class JojoConfigServerApplication {
    private static final Logger logger = LoggerFactory.getLogger(JojoConfigServerApplication.class);

    public static void main(String[] args) {
        logger.debug("jojo-config-server start up!!");

        SpringApplication.run(JojoConfigServerApplication.class, args);
    }

}
