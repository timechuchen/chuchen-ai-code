package com.chuchen.chuchenaicodebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class ChuchenAiCodeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChuchenAiCodeBackendApplication.class, args);
    }

}
