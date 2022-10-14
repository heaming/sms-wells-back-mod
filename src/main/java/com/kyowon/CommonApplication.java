package com.kyowon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties()
@SpringBootApplication(scanBasePackages = "com")
public class CommonApplication {

    public static void main(String args[]) {
        SpringApplication.run(CommonApplication.class, args);
    }
}
