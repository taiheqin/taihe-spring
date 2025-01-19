package com.taihe.springframework.beans.factory;


public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
