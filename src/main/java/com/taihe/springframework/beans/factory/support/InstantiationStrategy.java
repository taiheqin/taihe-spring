package com.taihe.springframework.beans.factory.support;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author qinth
 * @since 2024/7/9 9:43
 **/
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
