package com.plixiaofei.community.util;

import java.util.UUID;

/**
 * Created on 2022/4/22 by plixiaofei
 */
public class MyUtil {
    public static String randomFileName(String originName) {
        String ext = null;
        if (originName != null) {
            ext = originName.substring(originName.lastIndexOf('.') + 1);
        }
        return UUID.randomUUID() + "." + ext;
    }
}
