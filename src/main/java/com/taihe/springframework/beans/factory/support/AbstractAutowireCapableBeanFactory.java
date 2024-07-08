package com.taihe.springframework.beans.factory.support;

import com.taihe.springframework.BeansException;
import com.taihe.springframework.beans.factory.config.BeanDefinition;

/**
 * @author qinth
 * @since 2024/7/8 17:11
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object creatBean(String beanName, BeanDefinition beanDefinition) throws BeansException{
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }
}
