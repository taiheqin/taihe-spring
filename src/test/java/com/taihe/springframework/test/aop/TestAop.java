package com.taihe.springframework.test.aop;

import com.taihe.springframework.aop.AdvisedSupport;
import com.taihe.springframework.aop.TargetSource;
import com.taihe.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.taihe.springframework.aop.framework.Cglib2AopProxy;
import com.taihe.springframework.aop.framework.JdkDynamicAopProxy;
import com.taihe.springframework.test.bean.UserService;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author qinth
 * @since 2025/1/22 16:33
 **/
public class TestAop {

    @Test
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.taihe.springframework.test.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));

        // true、true
    }

    @Test
    public void test_dynamic() {
        // 目标对象
        UserServiceInterface userService = new UserServiceImpl();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.taihe.springframework.test.aop.UserServiceInterface.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        UserServiceInterface proxy_jdk = (UserServiceInterface) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println(proxy_jdk.queryUserInfo());

        System.out.println("-----------------------------------------");

        // 代理对象(Cglib2AopProxy)
        UserServiceInterface proxy_cglib = (UserServiceInterface) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println(proxy_cglib.register("he"));
    }
}
