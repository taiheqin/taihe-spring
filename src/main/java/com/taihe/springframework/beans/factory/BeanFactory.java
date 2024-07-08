package com.taihe.springframework.beans.factory;

import com.taihe.springframework.BeansException;
import com.taihe.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qinth
 * @since 2024/7/8 15:51
 **/
public interface BeanFactory {

    Object getBean(String name) throws BeansException;
}
