package com.taihe.springframework.beans.factory;

import com.taihe.springframework.beans.BeansException;

/**
 * @author qinth
 * @since 2024/7/8 15:51
 **/
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;
}
