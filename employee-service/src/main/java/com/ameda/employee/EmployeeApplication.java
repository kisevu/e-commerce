package com.ameda.employee;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication

public class EmployeeApplication {
    @Value("${address-service.base_url}")
    private String baseURL;
    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public WebClient webClient(){
        return WebClient
                .builder()
                .baseUrl(baseURL)
                .build();
    }
}
