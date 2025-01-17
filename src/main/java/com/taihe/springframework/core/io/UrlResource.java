package com.taihe.springframework.core.io;

import com.taihe.springframework.beans.BeansException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author qinth
 * @since 2024/7/9 16:48
 **/
public class UrlResource implements Resource {

    private final URL url;

    public UrlResource(URL url) {
        if (url == null) {
            throw new BeansException("Failed to construct UrlResource cause by null url");
        }
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = this.url.openConnection();
        try {
            return urlConnection.getInputStream();
        } catch (IOException e) {

            throw new BeansException("Failed to get input stream in UrlResource with: " + url.getPath(), e);
        } finally {
            if (urlConnection instanceof HttpURLConnection) {
                ((HttpURLConnection) urlConnection).disconnect();
            }
        }
    }
}
