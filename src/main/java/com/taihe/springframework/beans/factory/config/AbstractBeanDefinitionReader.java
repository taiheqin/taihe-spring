package com.taihe.springframework.beans.factory.config;

import com.taihe.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.taihe.springframework.core.io.DefaultResourceLoader;
import com.taihe.springframework.core.io.ResourceLoader;

/**
 * @author qinth
 * @since 2025/1/16 16:11
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
