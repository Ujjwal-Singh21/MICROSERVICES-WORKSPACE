package com.seleniumexpress.employeeservice.openfeignclients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

import feign.Feign;

//@LoadBalancerClient(value = "ADDRESS-SERVICE", configuration = MyCustomLoadBalancerConfiguration.class) // -> CUSTOM LOAD BALANCING

//@LoadBalancerClient(value = "ADDRESS-SERVICE") // -> DEFAULT ROUND ROBIN LOAD BALANCING
public class AddressServiceLoadBalancer {

	@LoadBalanced
	@Bean
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}

}
