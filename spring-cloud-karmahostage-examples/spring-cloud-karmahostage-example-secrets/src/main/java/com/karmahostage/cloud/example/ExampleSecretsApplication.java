package com.karmahostage.cloud.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ExampleSecretsApplication {

    @Value("${application}")
    private String application;

    @PostConstruct
    public void init() {
        System.out.println("Your application secret: " + application);
    }

    public static void main(String[] args) {
        SpringApplication.run(ExampleSecretsApplication.class);
    }
}
