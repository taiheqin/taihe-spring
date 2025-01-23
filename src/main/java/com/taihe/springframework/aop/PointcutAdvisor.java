package com.taihe.springframework.aop;

/**
 * @author qinth
 * @since 2025/1/23 11:15
 **/
public interface PointcutAdvisor extends Advisor {

    /**
     * Get the Pointcut that drives this advisor
     *
     * @return {@link Pointcut} which drives this advisor
     */
    Pointcut getPointcut();
}
