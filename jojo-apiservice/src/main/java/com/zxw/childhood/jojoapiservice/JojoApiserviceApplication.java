package com.zxw.childhood.jojoapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class JojoApiserviceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(JojoApiserviceApplication.class, args);
       /* while(true) {
            //当动态配置刷新时，会更新到 Enviroment中，因此这里每隔一秒中从Enviroment中获取配置
            String userName = applicationContext.getEnvironment().getProperty("druid.login.loginUsername");
            String userAge = applicationContext.getEnvironment().getProperty("spring.datasource.type");
            System.err.println("user name :" + userName + "; age: " + userAge);
        }*/
    }

}
