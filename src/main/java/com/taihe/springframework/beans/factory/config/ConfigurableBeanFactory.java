package com.taihe.springframework.beans.factory.config;

import com.taihe.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author qinth
 * @since 2025/1/17 11:35
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
