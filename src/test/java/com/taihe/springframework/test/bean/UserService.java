package com.taihe.springframework.test.bean;

/**
 * @author qinth
 * @since 2024/7/8 15:54
 **/
public class UserService {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserService(String name) {
        this.name = name;
    }

    public void queryService() {
        System.out.printf("query %s's info%n", name);
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(name);
        return stringBuilder.toString();
    }
}
