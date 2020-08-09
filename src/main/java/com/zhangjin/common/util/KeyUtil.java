package com.zhangjin.common.util;

import javax.crypto.SecretKey;

/**
 * Created by siiiriu on 2020/8/9.
 */
public final class KeyUtil {

    private static SecretKey lastKey;

    private static SecretKey key;


    public static SecretKey getLastKey() {
        return lastKey;
    }

    public static SecretKey getKey() {
        return key;
    }
}
