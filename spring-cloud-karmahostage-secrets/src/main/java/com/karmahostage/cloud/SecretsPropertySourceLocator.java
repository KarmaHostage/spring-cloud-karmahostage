package com.karmahostage.cloud;

import com.karmahostage.client.Karmahostage;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

@Order(1)
public class SecretsPropertySourceLocator implements PropertySourceLocator {

    private final Karmahostage client;
    private final SecretsConfigProperties properties;

    public SecretsPropertySourceLocator(Karmahostage client,
                                        SecretsConfigProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    @Override
    public PropertySource locate(Environment environment) {
        if (environment instanceof ConfigurableEnvironment) {

            CompositePropertySource composite = new CompositePropertySource(
                    "composite-secrets");
            this.properties.getPaths()
                    .forEach(s -> composite.addFirstPropertySource(getKarmahostagePropertySource(s)));
            return composite;
        }
        return null;
    }

    private MapPropertySource getKarmahostagePropertySource(String path) {
        return new SecretsPropertySource(this.client, path);
    }
}