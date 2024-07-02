package com.example.xprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class XpriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XpriceApplication.class, args);
    }

}
