package com.github.lybgeek.comsumer;


import com.github.lybgeek.feign.ext.annotation.EnableLybGeekFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableLybGeekFeignClients(basePackages = "com.github.lybgeek")
public class ConsumerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }


}
