package com.taihe.springframework.beans.factory.config;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.BeanFactory;

/**
 * @author qinth
 * @since 2025/1/17 15:10
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
