package com.karmahostage.cloud;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.cloud.karmahostage.encryptedproperties")
public class KarmahostageEncryptedPropertiesConfigProperties {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}