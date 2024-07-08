package com.taihe.springframework.beans.factory.support;

import com.taihe.springframework.BeansException;
import com.taihe.springframework.beans.factory.BeanFactory;
import com.taihe.springframework.beans.factory.config.BeanDefinition;

/**
 * @author qinth
 * @since 2024/7/8 16:59
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return creatBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object creatBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
