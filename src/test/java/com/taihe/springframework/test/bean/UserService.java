package com.taihe.springframework.test.bean;

import com.taihe.springframework.beans.factory.DisposableBean;
import com.taihe.springframework.beans.factory.InitializingBean;

/**
 * @author qinth
 * @since 2024/7/8 15:54
 **/
public class UserService implements InitializingBean, DisposableBean {

    private String id;


    private String company;

    private String location;

    private UserDao userDao;

    public void queryUserInfo() {
        System.out.printf("------------query %s's info------\n " +
                        "name: %s, company: %s, location: %s%n",
                id, userDao.queryUserName(id), company, location);
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
}
