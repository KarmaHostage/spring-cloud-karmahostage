package com.karmahostage.cloud;

import com.karmahostage.client.Karmahostage;
import com.karmahostage.secret.response.ExposedSecretResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class KarmahostageSecretsPropertySource extends MapPropertySource {

    private static final Log LOG = LogFactory.getLog(KarmahostageSecretsPropertySource.class);

    private static final String PREFIX = "secrets";

    public KarmahostageSecretsPropertySource(Karmahostage client, String path) {
        super(getSourceName(client, path),
                getSourceData(client, path));
    }

    private static String getSourceName(final Karmahostage client,
                                        final String path) {
        return PREFIX + ".karmahostage" + "/" + path;
    }

    private static Map<String, Object> getSourceData(Karmahostage client,
                                                     String path) {
        Map<String, Object> result = new HashMap<>();

        try {
            final ExposedSecretResponse secret = client.secrets().retrieveByKey(path);
            putAll(secret, result);
        } catch (Exception e) {
            LOG.warn(String.format("Can't read secret with name: [%s] (cause: %s). Ignoring", path, e.getMessage()));
        }
        return result;
    }

    private static void putAll(ExposedSecretResponse secret, Map<String, Object> result) {
        if (secret != null) {
            result.put(secret.getKey(), secret.getValue());
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {name='" + this.name + "'}";
    }

}