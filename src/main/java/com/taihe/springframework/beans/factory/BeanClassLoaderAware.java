package com.taihe.springframework.beans.factory;

/**
 * @author qinth
 * @since 2025/1/20 15:46
 **/
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
