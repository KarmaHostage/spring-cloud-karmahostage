package com.karmahostage.cloud;

import com.karmahostage.client.Karmahostage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnKarmahostageEnabled
@EnableConfigurationProperties(KarmahostageProperties.class)
public class KarmahostageAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(Karmahostage.class)
    public Karmahostage karmahostage(KarmahostageProperties karmahostageProperties) {
        return Karmahostage.builder()
                .apikey(karmahostageProperties.getApiKey())
                .build();
    }
}