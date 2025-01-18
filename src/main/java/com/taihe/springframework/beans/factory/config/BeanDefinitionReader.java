package com.taihe.springframework.beans.factory.config;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.taihe.springframework.core.io.Resource;
import com.taihe.springframework.core.io.ResourceLoader;

/**
 * @author qinth
 * @since 2025/1/16 16:06
 **/
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations);
}
