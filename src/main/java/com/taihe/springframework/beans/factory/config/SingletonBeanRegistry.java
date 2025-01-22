package com.taihe.springframework.beans.factory.config;

/**
 * interface: registry singleton bean.
 *
 * @author qinth
 * @since 2024/7/8 16:54
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void destroySingletons();

    void registerSingleton(String beanName, Object singletonObject);
}
