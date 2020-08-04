package com.karmahostage.cloud.example;

import com.karmahostage.cloud.encryptedproperties.EncryptedValue;
import com.karmahostage.cloud.encryptedproperties.EncryptedValueAnnotationProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ExampleEncryptedPropertiesApplication {

    @EncryptedValue("${application.secret}")
    private String application;

    @PostConstruct
    public void init() {
        System.out.println("Your application secret: " + application);
    }

    public static void main(String[] args) {
        SpringApplication.run(ExampleEncryptedPropertiesApplication.class);
    }
}
