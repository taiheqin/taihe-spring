package com.taihe.springframework.test.aop;


import java.util.Random;

public class UserServiceImpl implements UserServiceInterface {

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "query user info then return taihe.\n";
    }

    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "register user for '" + userName + "' success.\n";
    }

}