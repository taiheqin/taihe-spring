package com.taihe.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.DisposableBean;
import com.taihe.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;
import java.util.Objects;

public class DisposableBeanAdapter implements DisposableBean {

    /**
     * the bean having destroy method
     */
    private final Object bean;

    /**
     * bean's name
     */
    private final String beanName;

    /**
     * the name of the function invoking after bean destroy
     */
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.beanName = beanName;
        this.bean = bean;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // invoke destroy()
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // invoke the function named destroyMethodName. check twice to avoid invoking the destroy function twice
        if (StrUtil.isNotBlank(destroyMethodName)
                && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (Objects.isNull(destroyMethod)) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
