package com.codesurfers.duthealthcareclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration
public class DutHealthcareClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(DutHealthcareClinicApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Bean
			public WebMvcConfigurer corsConfigurer() {
				return new WebMvcConfigurer() {
					@Override
					public void addCorsMappings(CorsRegistry registry) {
						registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
					}
				};
			}

			@Bean
			public RestTemplate getRestTemplate() {
				return new RestTemplate();
			}
		};
	}

}
