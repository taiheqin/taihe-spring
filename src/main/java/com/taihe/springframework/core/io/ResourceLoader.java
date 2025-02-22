package com.taihe.springframework.core.io;

/**
 * @author qinth
 * @since 2024/7/9 16:55
 **/
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
