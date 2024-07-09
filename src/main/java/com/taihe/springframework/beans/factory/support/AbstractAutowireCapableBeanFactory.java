package com.taihe.springframework.beans.factory.support;

import com.taihe.springframework.BeansException;
import com.taihe.springframework.beans.factory.config.BeanDefinition;
import sun.security.jca.GetInstance;

import java.lang.reflect.Constructor;

/**
 * @author qinth
 * @since 2024/7/8 17:11
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object creatBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {

        if (args == null || args.length == 0) {
            return getInstantiationStrategy().instantiate(beanDefinition, beanName, null, args);
        }
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            if (ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
