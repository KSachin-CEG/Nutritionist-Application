package com.globallogic.favfoodservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
public class FavFoodServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavFoodServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean(name = "internalApiBean")
	public RestTemplate getInternalRestTemplate() {
		/*
		 * HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new
		 * HttpComponentsClientHttpRequestFactory();
		 * clientHttpRequestFactory.setConnectTimeout(3000);
		 * clientHttpRequestFactory.setReadTimeout(3000);
		 */
		return new RestTemplate();
	}
	
	@Bean(name = "externalApiBean")
	public RestTemplate getExternalRestTemplate() {
		return new RestTemplate();
	}
}
