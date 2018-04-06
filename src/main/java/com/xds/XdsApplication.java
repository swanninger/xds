package com.xds;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class XdsApplication {

	public static void main(String[] args) {
		//ApplicationContext ctx = SpringApplication.run(XdsApplication.class, args);

		SpringApplicationBuilder builder = new SpringApplicationBuilder(XdsApplication.class);
		builder.headless(false).run(args);
	}
}