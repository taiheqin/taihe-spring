package com.taihe.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.PropertyValue;
import com.taihe.springframework.beans.PropertyValues;
import com.taihe.springframework.beans.factory.*;
import com.taihe.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.taihe.springframework.beans.factory.config.BeanDefinition;
import com.taihe.springframework.beans.factory.config.BeanPostProcessor;
import com.taihe.springframework.beans.factory.config.BeanReference;

import javax.naming.InitialContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author qinth
 * @since 2024/7/8 17:11
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            // create the bean
            bean = createBeanInstance(beanDefinition, beanName, args);

            // fill bean's properties
            applyPropertyValue(beanName, bean, beanDefinition);

            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException(String.format("Instantiation of bean[%s] failed", beanName), e);
        }

        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }

        return bean;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {

        // invokeAwareMethods
        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }

        // 1. deal BeanPostProcessor Before
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // invoke init methods
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    /**
     * invoke afterPropertiesSet() and reflect the init-method in beanDefinitions
     *
     * @param beanName       bean's name
     * @param bean           the object of bean
     * @param beanDefinition bean's definitions
     * @throws Exception could not find init-method or invoke exception
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // invoke afterPropertiesSet()
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // invoke function named init-method
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotBlank(initMethodName)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (Objects.isNull(initMethod)) {
                throw new BeansException("Could not find an init method named '" + initMethodName
                        + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
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

    protected void applyPropertyValue(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException(String.format("Error setting [%s]'s property values", beanName), e);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    private static void setFieldValue(Object bean, String name, Object value) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Field field = getField(beanClass, name);
        if (field == null) {
            throw new BeansException(String.format("Failed to find field named '%s' in class '%s' or its superclasses", name, beanClass.getName()));
        }
        field.setAccessible(true);
        try {
            field.set(bean, value);
        } catch (IllegalAccessException e) {
            throw new BeansException(String.format("Failed to set value '%s' to field '%s' in class '%s'", value, name, beanClass.getName()), e);
        }
    }

    private static Field getField(Class<?> clazz, String name) {
        if (clazz == null) {
            return null;
        }
        try {
            return clazz.getDeclaredField(name);
        } catch (NoSuchFieldException e) {

            // Field not found in the current class, try the superclass
            Field field = getField(clazz.getSuperclass(), name);

            return field;
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(existingBean, beanName);
            if (!Objects.isNull(current)) {
                return current;
            }
        }
        return existingBean;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(existingBean, beanName);
            if (!Objects.isNull(current)) {
                return current;
            }
        }
        return existingBean;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (!beanDefinition.isPrototype()) {
            return;
        }

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }
}
