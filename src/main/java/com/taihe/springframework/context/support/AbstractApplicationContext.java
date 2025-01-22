package com.taihe.springframework.context.support;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.taihe.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.taihe.springframework.beans.factory.config.BeanPostProcessor;
import com.taihe.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.taihe.springframework.context.ApplicationContextAwareProcessor;
import com.taihe.springframework.context.ApplicationEvent;
import com.taihe.springframework.context.ApplicationListener;
import com.taihe.springframework.context.ConfigurableApplicationContext;
import com.taihe.springframework.context.event.ApplicationEventMulticaster;
import com.taihe.springframework.context.event.ContextClosedEvent;
import com.taihe.springframework.context.event.ContextRefreshedEvent;
import com.taihe.springframework.context.event.SimpleApplicationEventMulticaster;
import com.taihe.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @author qinth
 * @since 2025/1/17 15:01
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

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

        // init multicaster
        initApplicationEventMulticaster();

        // register listeners
        registerListeners();

        // complete refreshing the factory(publish the ContextRefreshedEvent)
        finishRefresh();

    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // publish close event
        publishEvent(new ContextClosedEvent(this));

        // to destroy bean
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

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }


}
