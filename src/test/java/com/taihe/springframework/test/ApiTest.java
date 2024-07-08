package com.taihe.springframework.test;

import com.taihe.springframework.BeanDefinition;
import com.taihe.springframework.BeanFactory;
import com.taihe.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author qinth
 * @since 2024/7/8 15:56
 **/
public class ApiTest {

    @Test
    public void testBeanFactory() {
        // 1.init beanFactory
        BeanFactory beanFactory = new BeanFactory();

        // 2.registry bean
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        // 3.get bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryService();

    }
}
