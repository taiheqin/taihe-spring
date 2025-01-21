package com.taihe.springframework.test;

import com.taihe.springframework.beans.PropertyValue;
import com.taihe.springframework.beans.PropertyValues;
import com.taihe.springframework.beans.factory.config.BeanDefinition;
import com.taihe.springframework.beans.factory.config.BeanReference;
import com.taihe.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.taihe.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.taihe.springframework.context.support.ClassPathXmlApplicationContext;
import com.taihe.springframework.test.bean.UserDao;
import com.taihe.springframework.test.bean.UserService;
import com.taihe.springframework.test.scope.ScopeUserService;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

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

    @Test
    public void testAware() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:initAndDestroy.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();

    }

    @Test
    public void test_prototype() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:scope.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        ScopeUserService userService01 = applicationContext.getBean("userService", ScopeUserService.class);
        ScopeUserService userService02 = applicationContext.getBean("userService", ScopeUserService.class);
        System.out.println("---------------userService01");
        userService01.queryUserInfo();
        System.out.println("---------------userService02");
        userService02.queryUserInfo();

        System.out.println("---------------");
        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println("---------------");
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println("---------------");
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
        System.out.println("---------------");
        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
        System.out.println("-------------");

    }

    @Test
    public void test_factory_bean() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:scope.xml");
        applicationContext.registerShutdownHook();

        // 2. 调用代理方法
        ScopeUserService userService = applicationContext.getBean("userService", ScopeUserService.class);
        userService.queryUserInfo();
    }
}
