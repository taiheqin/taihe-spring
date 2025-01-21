package com.taihe.springframework.test.scope;

import com.taihe.springframework.beans.BeansException;
import com.taihe.springframework.beans.factory.*;
import com.taihe.springframework.context.ApplicationContext;
import com.taihe.springframework.context.ApplicationContextAware;
import com.taihe.springframework.test.bean.UserDao;

/**
 * @author qinth
 * @since 2024/7/8 15:54
 **/
public class ScopeUserService  {

    private String id;

    private String company;

    private String location;

    private ScopeUserDao userDao;


    public void queryUserInfo() {
        System.out.printf("------------query %s's info------\n " +
                        "name: %s, company: %s, location: %s%n",
                id, userDao.queryUserName(id), company, location);

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ScopeUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(ScopeUserDao userDao) {
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
