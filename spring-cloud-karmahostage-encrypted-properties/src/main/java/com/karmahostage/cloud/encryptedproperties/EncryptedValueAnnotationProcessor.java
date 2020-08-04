package com.karmahostage.cloud.encryptedproperties;

import com.karmahostage.client.Karmahostage;
import com.karmahostage.client.key.Key;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ReflectionUtils;

public class EncryptedValueAnnotationProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;
    private final Key karmahostageKey;

    public EncryptedValueAnnotationProcessor(ApplicationContext applicationContext, Key karmahostageKey) {
        this.applicationContext = applicationContext;
        this.karmahostageKey = karmahostageKey;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        this.scanDataAccessAnnotation(bean, beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    protected void scanDataAccessAnnotation(Object bean, String beanName) {
        this.configureFieldInjection(bean);
    }

    private void configureFieldInjection(Object bean) {
        Class<?> managedBeanClass = bean.getClass();
        ReflectionUtils.FieldCallback fieldCallback = new EncryptedValueFieldCallback(bean, applicationContext, karmahostageKey);
        ReflectionUtils.doWithFields(managedBeanClass, fieldCallback);
    }
}
