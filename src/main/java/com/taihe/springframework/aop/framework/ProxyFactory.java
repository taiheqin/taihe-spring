package com.taihe.springframework.aop.framework;

import com.taihe.springframework.aop.AdvisedSupport;

/**
 * @author qinth
 * @since 2025/1/23 11:29
 **/
public class ProxyFactory {

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createProxy().getProxy();
    }

    private AopProxy createProxy() {

        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
