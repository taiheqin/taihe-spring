package com.taihe.springframework.test;

import com.taihe.springframework.beans.factory.config.BeanDefinition;
import com.taihe.springframework.beans.factory.BeanFactory;
import com.taihe.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.taihe.springframework.test.bean.UserService;
import org.junit.Test;

import java.util.Objects;

/**
 * @author qinth
 * @since 2024/7/8 15:56
 **/
public class ApiTest {

    /**
     * bean registry and get from factory
     */
    @Test
    public void testBeanFactory() {
        // 1.init beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2.registry bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        // 3.get bean
        //UserService userService = (UserService) beanFactory.getBean("userService");
        //userService.queryService();
        UserService userService = (UserService) beanFactory.getBean("userService", "taihe");
        userService.queryService();

        // 4.get bean again and compare
        Object userServiceGetFromFactoryAgain = beanFactory.getBean("userService", "taiheqin");
        System.out.printf(String.format("userService: '%s' \n" +
                        "userServiceGetFromFactoryAgain: '%s'\n" +
                        "userService equals userServiceGetFromFactoryAgain: %s",
                userService,
                userServiceGetFromFactoryAgain,
                Objects.equals(userService, userServiceGetFromFactoryAgain)));

    }
}
