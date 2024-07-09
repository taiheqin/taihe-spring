package com.taihe.springframework.beans.factory.config;

/**
 * @author qinth
 * @since 2024/7/9 11:26
 **/
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
