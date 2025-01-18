package com.taihe.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;
import com.taihe.springframework.beans.BeansException;

/**
 * @author qinth
 * @since 2025/1/17 11:43
 **/
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException;
}
