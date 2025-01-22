package com.taihe.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author qinth
 * @since 2025/1/22 16:14
 **/
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
