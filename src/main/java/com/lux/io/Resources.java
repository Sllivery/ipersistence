package com.lux.io;

import java.io.InputStream;

/**
 * @Author Xingsiwei
 * @Create 2020-12-2020/12/10
 **/
public class Resources {
    public static InputStream getResourceAsStream(String path){
        InputStream inputStream = Resources.class.getResourceAsStream(path);
        return inputStream;
    }
}
