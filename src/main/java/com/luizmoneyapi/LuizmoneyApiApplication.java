package com.luizmoneyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.luizmoneyapi.config.property.LuizMoneyApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(LuizMoneyApiProperty.class)
public class LuizmoneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuizmoneyApiApplication.class, args);
	}
}
