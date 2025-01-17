package com.taihe.springframework.core.io;

import com.taihe.springframework.BeansException;
import com.taihe.springframework.beans.utils.ClassUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author qinth
 * @since 2024/7/9 16:33
 **/
public class ClassPathResource implements Resource {

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        if (path == null) {
            throw new BeansException("Failed to load 'ClassPathResource' cause by empty path");
        }
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }


    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (inputStream == null) {
            throw new IOException(String.format("Failed to get input stream with path '%s'", path));
        }
        return inputStream;
    }
}
