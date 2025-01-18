package com.taihe.springframework.beans.factory;

import com.taihe.springframework.beans.BeansException;

import java.util.Map;

/**
 * @author qinth
 * @since 2025/1/17 11:47
 **/
public interface ListableBeanFactory  extends BeanFactory {

    /**
     * get beans by class type
     *
     * @param type class type
     * @param <T> any class
     * @return bean
     * @throws BeansException exception as not found type
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * get all kind of bean names
     *
     * @return array
     */
    String[] getBeanDefinitionNames();
}
