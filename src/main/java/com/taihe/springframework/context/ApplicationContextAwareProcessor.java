package com.taihe.springframework.context;

import cn.hutool.core.bean.BeanException;
import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.Aware;
import com.taihe.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author qinth
 * @since 2025/1/20 15:48
 **/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}
