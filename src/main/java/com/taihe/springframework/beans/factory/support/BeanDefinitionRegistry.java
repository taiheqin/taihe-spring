package com.taihe.springframework.beans.factory.support;

import com.taihe.springframework.beans.factory.config.BeanDefinition;

/**
 * @author qinth
 * @since 2024/7/8 17:23
 **/
public interface BeanDefinitionRegistry {

    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
