package com.lntu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.lntu.dao")
public class WechatMallApplication {
	public static void main(String[] args) {
		SpringApplication.run(WechatMallApplication.class, args);
	}
}