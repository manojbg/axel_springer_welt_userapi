package com.axelspringer.welt.weltuserapi.appStarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication(scanBasePackages="com.axelspringer.welt")
public class WeltuserapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeltuserapiApplication.class, args);
	}

	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}
}
