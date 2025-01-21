package com.taihe.springframework.test.scope;

import com.taihe.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qinth
 * @since 2025/1/21 10:35
 **/
public class ProxyBeanFactory implements FactoryBean<ScopeUserDao> {


    @Override
    public ScopeUserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {

            if ("toString".equals(method.getName())) {
                return this.toString();
            }

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "taihe");
            hashMap.put("10002", "tai");
            hashMap.put("10003", "he");
            if (args != null && args[0] != null) {
                return "Proxied " + method.getName() + "; " + hashMap.get(args[0].toString());
            }

            return "Proxied " + method.getName() + ", but args[0] is null";
        };
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        ScopeUserDao scopeUserDao = (ScopeUserDao) Proxy.newProxyInstance(
                contextClassLoader, new Class[]{ScopeUserDao.class}, handler);
        return scopeUserDao;
    }

    @Override
    public Class<ScopeUserDao> getObjectType() {
        return ScopeUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
