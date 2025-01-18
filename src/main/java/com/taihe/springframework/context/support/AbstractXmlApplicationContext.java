package com.taihe.springframework.context.support;

import com.taihe.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.taihe.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Objects;

/**
 * @author qinth
 * @since 2025/1/17 16:16
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (!Objects.isNull(configLocations)) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
