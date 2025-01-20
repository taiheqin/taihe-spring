package com.taihe.springframework.context.support;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.taihe.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.taihe.springframework.beans.factory.config.BeanPostProcessor;
import com.taihe.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.taihe.springframework.context.ApplicationContextAwareProcessor;
import com.taihe.springframework.context.ConfigurableApplicationContext;
import com.taihe.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author qinth
 * @since 2025/1/17 15:01
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // create bean factory and load bean definition
        refreshBeanFactory();

        // get bean factory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // add ApplicationContextAwareProcessor. Make beans that inherit from ApplicationContextAware aware of their owning ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // Invoke factory processors registered as beans in the context.
        invokeBeanFactoryPostProcessors(beanFactory);

        // register bean extends from BeanPostProcessor before another bean
        registerBeanPostProcessors(beanFactory);

        // instantiate singletons in advance
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {

        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap =
                beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);

        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

}
