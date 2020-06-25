package com.karmahostage.cloud;

import com.karmahostage.client.Karmahostage;
import com.karmahostage.client.secret.Secret;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class SecretsPropertySource extends MapPropertySource {

    private static final Log LOG = LogFactory.getLog(SecretsPropertySource.class);

    private static final String PREFIX = "secrets";

    public SecretsPropertySource(Karmahostage client, String path) {
        super(getSourceName(client, path),
                getSourceData(client, path));
    }

    private static String getSourceName(final Karmahostage client,
                                        final String path) {
        return PREFIX + "karmahostage" + "/" + path;
    }

    private static Map<String, Object> getSourceData(Karmahostage client,
                                                     String path) {
        Map<String, Object> result = new HashMap<>();

        try {
            final Secret secret = client.secrets().retrieveByKey(path);
            putAll(secret, result);
        } catch (Exception e) {
            LOG.warn(String.format("Can't read secret with name: [%s] (cause: %s). Ignoring", path, e.getMessage()));
        }
        return result;
    }

    private static void putAll(Secret secret, Map<String, Object> result) {
        if (secret != null) {
            result.put(secret.getKey(), secret.getValue());
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {name='" + this.name + "'}";
    }

}