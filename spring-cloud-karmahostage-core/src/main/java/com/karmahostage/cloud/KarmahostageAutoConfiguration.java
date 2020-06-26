package com.karmahostage.cloud;

import com.karmahostage.client.Karmahostage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Objects.requireNonNull;

@Configuration(proxyBeanMethods = false)
@ConditionalOnKarmahostageEnabled
@EnableConfigurationProperties(KarmahostageProperties.class)
public class KarmahostageAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Karmahostage.class)
    public Karmahostage karmahostage(KarmahostageProperties karmahostageProperties) {
        requireNonNull(karmahostageProperties.getApiKey(), "You haven't specified an API key to connect to karmahostage. Please visit https://dashboard.karmahostage.com to register for an api key. You can set it using spring.cloud.karmahostage.api-key=API_KEY in your bootstrap.properties");
        return Karmahostage.builder()
                .apikey(karmahostageProperties.getApiKey())
                .build();
    }
}