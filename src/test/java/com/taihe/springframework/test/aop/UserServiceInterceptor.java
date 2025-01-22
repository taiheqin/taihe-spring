package com.taihe.springframework.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class UserServiceInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            System.out.println("AOP Begin---");
            System.out.println("Method name: " + invocation.getMethod());
            System.out.println("Method invoked in " + (System.currentTimeMillis() - start) + "ms");
            System.out.println("AOP End---\r\n");
        }
    }

}