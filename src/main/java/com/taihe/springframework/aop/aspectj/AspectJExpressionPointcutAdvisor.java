package com.taihe.springframework.aop.aspectj;

import com.taihe.springframework.aop.Pointcut;
import com.taihe.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

import java.util.Objects;

/**
 * @author qinth
 * @since 2025/1/23 11:21
 **/
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    /**
     * the pointcut
     */
    private AspectJExpressionPointcut pointcut;

    /**
     * the advice
     */
    private Advice advice;

    /**
     * the expression
     */
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (Objects.isNull(pointcut)) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
