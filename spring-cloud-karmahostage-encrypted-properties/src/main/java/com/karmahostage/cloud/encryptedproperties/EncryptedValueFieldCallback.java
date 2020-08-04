package com.karmahostage.cloud.encryptedproperties;

import com.karmahostage.client.key.Key;
import com.karmahostage.cryptography.response.DecryptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class EncryptedValueFieldCallback implements ReflectionUtils.FieldCallback {
    private static Logger logger = LoggerFactory.getLogger(EncryptedValueFieldCallback.class);

    private final Object bean;
    private final ApplicationContext applicationContext;
    private Key karmahostageKey;

    public EncryptedValueFieldCallback(final Object bean,
                                       final ApplicationContext applicationContext,
                                       final Key karmahostageKey) {
        this.bean = bean;
        this.applicationContext = applicationContext;
        this.karmahostageKey = karmahostageKey;
    }

    @Override
    public void doWith(Field field)
            throws IllegalArgumentException, IllegalAccessException {
        if (!field.isAnnotationPresent(EncryptedValue.class)) {
            return;
        }
        ReflectionUtils.makeAccessible(field);
        Type fieldGenericType = field.getGenericType();
        String value = field.getDeclaredAnnotation(EncryptedValue.class).value();
        field.set(bean, decrypt(fetchValue(value)));
    }

    private String decrypt(String value) {
        try {
            final DecryptionResponse decrypt = karmahostageKey.decrypt(value);
            return decrypt != null ? decrypt.getPlainText() : null;
        } catch (Exception exception) {
            logger.error("Unable to decrypt " + value, exception);
            return null;
        }
    }

    private String fetchValue(String value) {
        try {
            if (value == null) {
                return null;
            } else if (value.startsWith("${") && value.endsWith("}")) {
                return applicationContext.getEnvironment().getProperty(value.substring(
                        value.indexOf("{") + 1,
                        value.lastIndexOf("}")
                ));
            } else {
                return applicationContext.getEnvironment().getProperty(value);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}