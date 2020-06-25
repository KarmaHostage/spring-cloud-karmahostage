package com.karmahostage.cloud;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Karmahostage properties.
 */
@ConfigurationProperties("spring.cloud.karmahostage")
public class KarmahostageProperties {

    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}