package com.taihe.springframework.beans.factory;

import com.taihe.springframework.beans.BeansException;

/**
 * @author qinth
 * @since 2025/1/20 15:44
 **/
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
