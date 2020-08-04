package com.karmahostage.cloud;

import com.karmahostage.client.Karmahostage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(value = "spring.cloud.karmahostage.enabled", matchIfMissing = true)
public class KarmahostageSecretsBootstrapConfiguration {

    @Configuration(proxyBeanMethods = false)
    @Import(KarmahostageAutoConfiguration.class)
    @EnableConfigurationProperties({KarmahostageSecretsConfigProperties.class})
    protected static class KarmahostagePropertySourceConfiguration {

        @Autowired
        private Karmahostage karmahostage;

        @Bean
        @ConditionalOnProperty(name = "spring.cloud.karmahostage.secrets.enabled",
                matchIfMissing = true)
        public KarmahostageSecretsPropertySourceLocator karmahostageSecretsPropertySourceLocator(
                KarmahostageSecretsConfigProperties properties) {
            return new KarmahostageSecretsPropertySourceLocator(this.karmahostage, properties);
        }
    }
}