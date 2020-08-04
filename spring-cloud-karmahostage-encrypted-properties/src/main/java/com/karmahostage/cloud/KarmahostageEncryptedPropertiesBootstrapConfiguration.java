package com.karmahostage.cloud;

import com.karmahostage.client.Karmahostage;
import com.karmahostage.cloud.encryptedproperties.EncryptedValueAnnotationProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Objects.requireNonNull;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(value = "spring.cloud.karmahostage.encryptedproperties.enabled", matchIfMissing = true)
@EnableConfigurationProperties({KarmahostageEncryptedPropertiesConfigProperties.class})
public class KarmahostageEncryptedPropertiesBootstrapConfiguration {

    @Bean
    public EncryptedValueAnnotationProcessor provideKarmahostage(final ApplicationContext applicationContext,
                                                                 final Karmahostage karmahostage,
                                                                 final KarmahostageEncryptedPropertiesConfigProperties properties) {
        requireNonNull(properties.getKey(), "You haven't specified a decryption key to decrypt @EncryptedValue fields. " +
                "Please visit https://dashboard.karmahostage.com to copy your key-id. You can set it using spring.cloud.encryptedproperties.key=KEY_ID in your application.properties");

        return new EncryptedValueAnnotationProcessor(applicationContext, karmahostage.keys().retrieve(
                properties.getKey()
        ));
    }
}