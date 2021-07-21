package com.home.shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.home.shop.config.StorageProperties;
import com.home.shop.service.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class J5shopApplication {

	public static void main(String[] args) {
		SpringApplication.run(J5shopApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args->{
			storageService.init();
		});
	}

}
