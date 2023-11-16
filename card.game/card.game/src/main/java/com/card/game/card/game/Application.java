package com.card.game.card.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = "com.card.game.card.game")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
