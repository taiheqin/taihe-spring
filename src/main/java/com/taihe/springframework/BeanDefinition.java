package com.taihe.springframework;

/**
 * @author qinth
 * @since 2024/7/8 15:48
 **/
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
