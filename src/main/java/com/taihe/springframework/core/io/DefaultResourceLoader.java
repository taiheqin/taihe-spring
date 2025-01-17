package com.taihe.springframework.core.io;

import cn.hutool.core.util.StrUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * @author qinth
 * @since 2025/1/16 15:57
 **/
public class DefaultResourceLoader implements ResourceLoader {

    @Override
    public Resource getResource(String location) {

        if (Objects.isNull(location) || StrUtil.isEmpty(location)) {
            throw new IllegalArgumentException("Location must not be null");
        }
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                return new FileSystemResource(location);
            }
        }
    }
}
