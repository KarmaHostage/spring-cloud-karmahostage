package com.karmahostage.cloud;

import com.karmahostage.client.Karmahostage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Optional;

@Order(1)
public class KarmahostageSecretsPropertySourceLocator implements PropertySourceLocator {

    private static final Logger LOG = LoggerFactory.getLogger(KarmahostageSecretsPropertySourceLocator.class);

    private final Karmahostage client;
    private final KarmahostageSecretsConfigProperties properties;

    public KarmahostageSecretsPropertySourceLocator(Karmahostage client,
                                                    KarmahostageSecretsConfigProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    @Override
    public PropertySource locate(Environment environment) {
        if (environment instanceof ConfigurableEnvironment) {

            CompositePropertySource composite = new CompositePropertySource(
                    "composite-secrets");
            if (this.properties.getPaths().isEmpty()) {
                LOG.info("No paths for karmahostage secrets were defined, trying to find path by application name");
                 final Optional<MapPropertySource> byApplicatonname = Optional.ofNullable(environment.getProperty("spring.application.name"))
                        .map(this::getKarmahostagePropertySource);

                byApplicatonname.ifPresent(composite::addFirstPropertySource);
            } else {
                this.properties.getPaths()
                        .forEach(s -> composite.addFirstPropertySource(getKarmahostagePropertySource(s)));
            }
            return composite;
        }
        return null;
    }

    private MapPropertySource getKarmahostagePropertySource(String path) {
        return new KarmahostageSecretsPropertySource(this.client, path);
    }
}