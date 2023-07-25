package com.seleniumexpress.employeeservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeAppConfig {

	@Value("${address-service.base.url}")
	private String baseUrl;

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@LoadBalanced // client-side load balancer using round-robin fashion with the help of spring cloud load balancer added while adding 
	// spring-cloud-netflix-client dependency
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebClient getWebClient() {
		return WebClient
				.builder()
				.baseUrl(baseUrl)
				.build();
	}

}
