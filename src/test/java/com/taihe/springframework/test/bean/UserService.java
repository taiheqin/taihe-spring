package com.taihe.springframework.test.bean;

/**
 * @author qinth
 * @since 2024/7/8 15:54
 **/
public class UserService {

    private String id;

    private UserDao userDao;

    public void queryService() {
        System.out.println(String.format("query %s's info: %s", id, userDao.queryUserName(id)));
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
}
