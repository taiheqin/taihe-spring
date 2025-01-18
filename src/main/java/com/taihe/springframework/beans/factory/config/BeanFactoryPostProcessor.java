package com.taihe.springframework.beans.factory.config;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.taihe.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author qinth
 * @since 2025/1/17 11:32
 **/
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
