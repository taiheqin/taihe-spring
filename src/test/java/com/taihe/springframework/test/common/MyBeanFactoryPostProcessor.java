package com.taihe.springframework.test.common;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.PropertyValue;
import com.taihe.springframework.beans.PropertyValues;
import com.taihe.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.taihe.springframework.beans.factory.config.BeanDefinition;
import com.taihe.springframework.beans.factory.config.BeanFactoryPostProcessor;

import java.util.Optional;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "change company's value from "
                + Optional.ofNullable(propertyValues.getPropertyValue("company")).map(PropertyValue::getValue).orElse("")
                + " to 字节跳动； by MyBeanFactoryPostProcessor"));
    }
}