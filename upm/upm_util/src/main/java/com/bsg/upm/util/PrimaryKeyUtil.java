package com.bsg.upm.util;

import java.util.UUID;

public class PrimaryKeyUtil {

    public static String get() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
