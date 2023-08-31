package com.yuxuan66.wife;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 老婆推送项目启动类
 * @author Sir丶雨轩
 * @since 2023/07/04
 */
@EnableScheduling
@SpringBootApplication
public class WifePushApp {

    public static void main(String[] args) {
        SpringApplication.run(WifePushApp.class,args);
    }
}
