package com.taihe.springframework.context;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.ListableBeanFactory;
import com.taihe.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;

/**
 * @author qinth
 * @since 2025/1/17 11:54
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * refresh bean container
     *
     * @throws BeansException exception
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
