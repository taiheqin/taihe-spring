package com.taihe.springframework.context;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.Aware;

/**
 * @author qinth
 * @since 2025/1/20 15:48
 **/
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
