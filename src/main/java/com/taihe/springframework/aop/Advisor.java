package com.taihe.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author qinth
 * @since 2025/1/23 11:14
 **/
public interface Advisor {

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws advice, etc.
     *
     * @return the advice that should apply if the pointcut matches
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();
}
