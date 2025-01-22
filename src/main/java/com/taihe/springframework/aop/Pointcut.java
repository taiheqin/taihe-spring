package com.taihe.springframework.aop;

/**
 * @author qinth
 * @since 2025/1/22 16:11
 **/
public interface Pointcut {

    /**
     * return the ClassFilter for this pointcut.
     *
     * @return the ClassFilter(never <code>null</code>)
     */
    ClassFilter getClassFilter();

    /**
     * return the methodMatcher for this pointcut.
     *
     * @return the MethodMatcher(never <code>null</code>)
     */
    MethodMatcher getMethodMatcher();
}
