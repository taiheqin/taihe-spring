package com.taihe.springframework.beans.factory;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.taihe.springframework.beans.factory.config.BeanDefinition;
import com.taihe.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author qinth
 * @since 2025/1/17 15:09
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
