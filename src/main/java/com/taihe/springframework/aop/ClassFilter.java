package com.taihe.springframework.aop;

/**
 * @author qinth
 * @since 2025/1/22 16:13
 **/
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     *
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    boolean matches(Class<?> clazz);
}
