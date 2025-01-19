package com.taihe.springframework.test;

import com.taihe.springframework.beans.PropertyValue;
import com.taihe.springframework.beans.PropertyValues;
import com.taihe.springframework.beans.factory.config.BeanDefinition;
import com.taihe.springframework.beans.factory.BeanFactory;
import com.taihe.springframework.beans.factory.config.BeanReference;
import com.taihe.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.taihe.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.taihe.springframework.context.support.ClassPathXmlApplicationContext;
import com.taihe.springframework.test.bean.UserDao;
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
        //BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        //beanFactory.registryBeanDefinition("userService", beanDefinition);
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 2.1registry UserService with property
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("id", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));

        // 3.get bean
        //UserService userService = (UserService) beanFactory.getBean("userService");
        //userService.queryService();
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();


    }

    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void testContext() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springContext.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();

    }

    @Test
    public void testInitAndDestroy() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:initAndDestroy.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();

    }
}
