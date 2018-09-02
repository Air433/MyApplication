package com.example.air.domain;

public class UserConfig {

    private static String token;

    private static int expire;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        UserConfig.token = token;
    }

    public static int getExpire() {
        return expire;
    }

    public static void setExpire(int expire) {
        UserConfig.expire = expire;
    }
}
