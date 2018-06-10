package com.xds;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableWebSecurity
public class XdsApplication {

	public static void main(String[] args) {
		//ApplicationContext ctx = SpringApplication.run(XdsApplication.class, args);

		SpringApplicationBuilder builder = new SpringApplicationBuilder(XdsApplication.class);
		builder.headless(false).run(args);
	}
}