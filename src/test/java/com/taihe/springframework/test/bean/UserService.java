package com.taihe.springframework.test.bean;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.*;
import com.taihe.springframework.context.ApplicationContext;
import com.taihe.springframework.context.ApplicationContextAware;

/**
 * @author qinth
 * @since 2024/7/8 15:54
 **/
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private String id;

    private String company;

    private String location;

    private UserDao userDao;

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    public void queryUserInfo() {
        System.out.printf("------------query %s's info------\n " +
                        "name: %s, company: %s, location: %s%n",
                id, userDao.queryUserName(id), company, location);
        System.out.println("------------other of userInfoService\n ApplicationContext:");
        System.out.println(this.applicationContext);
        System.out.println("BeanFactory:");
        System.out.println(this.beanFactory);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("----------- UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("----------- UserService.afterPropertiesSet");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("beanClassLoader: " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("beanName is:" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
