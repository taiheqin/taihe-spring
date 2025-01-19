package com.taihe.springframework.beans.factory;

public interface DisposableBean {

    void destroy() throws Exception;
}
