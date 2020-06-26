package com.karmahostage.cloud;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedList;
import java.util.List;

@ConfigurationProperties("spring.cloud.karmahostage.secrets")
public class KarmahostageSecretsConfigProperties {

    private List<String> paths = new LinkedList<>();

    public List<String> getPaths() {
        return this.paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }
}