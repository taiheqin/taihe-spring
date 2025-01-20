package com.taihe.springframework.beans.factory;

/**
 * @author qinth
 * @since 2025/1/20 15:47
 **/
public interface BeanNameAware extends Aware {

    void setBeanName(String name);
}
