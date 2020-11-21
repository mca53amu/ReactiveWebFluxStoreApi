package com.store.mobile.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class MobileStoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileStoreApiApplication.class, args);
	}

}
