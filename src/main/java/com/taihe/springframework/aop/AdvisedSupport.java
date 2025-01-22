package com.taihe.springframework.aop;


import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author qinth
 * @since 2025/1/22 16:37
 **/
public class AdvisedSupport {

    /**
     * the object(TargetSource) been proxied
     */
    private TargetSource targetSource;

    /**
     * method interceptor
     */
    private MethodInterceptor methodInterceptor;

    /**
     * method matcher
     */
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
