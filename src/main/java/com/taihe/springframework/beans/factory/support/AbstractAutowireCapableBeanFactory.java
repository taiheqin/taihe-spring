package com.taihe.springframework.beans.factory.support;

import com.taihe.springframework.BeansException;
import com.taihe.springframework.beans.PropertyValue;
import com.taihe.springframework.beans.PropertyValues;
import com.taihe.springframework.beans.factory.config.BeanDefinition;
import com.taihe.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

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

            applyPropertyValue(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException(String.format("Instantiation of bean[%s] failed", beanName), e);
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

                setFieldValue(bean, name, value);
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
}
