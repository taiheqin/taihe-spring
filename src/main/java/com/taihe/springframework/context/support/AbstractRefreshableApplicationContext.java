package com.taihe.springframework.context.support;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.taihe.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author qinth
 * @since 2025/1/17 15:31
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory(){
        return beanFactory;
    }
}
