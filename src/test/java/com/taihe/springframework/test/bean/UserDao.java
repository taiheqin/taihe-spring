package com.taihe.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qinth
 * @since 2024/7/9 11:37
 **/
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    public void initDataMethod(){
        System.out.println("----------- UserDao's init-method");
        hashMap.put("10001", "taihe");
        hashMap.put("10002", "tai");
        hashMap.put("10003", "he");
    }

    public void destroyDataMethod() {
        System.out.println("----------- UserDao's destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String id) {
        return hashMap.get(id);
    }
}
