package com.taihe.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author qinth
 * @since 2024/7/9 16:32
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;
}
