package com.taihe.springframework.test.common;


import cn.hutool.core.bean.BeanException;
import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.config.BeanPostProcessor;
import com.taihe.springframework.test.bean.UserService;

public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
