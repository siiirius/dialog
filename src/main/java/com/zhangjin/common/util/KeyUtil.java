package com.zhangjin.common.util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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


    private static final ScheduledExecutorService SCHEDULE = new ScheduledThreadPoolExecutor(1, r -> {
        Thread thread = new Thread(r);
        thread.setName("key_util_schedule_pool_thread");
        return thread;
    });

    static {
        genkey();
        SCHEDULE.scheduleAtFixedRate(KeyUtil::genkey, 15, 15, TimeUnit.MINUTES);
    }

    private static void genkey() {
        try {
            KeyGenerator keyGenerator = null;
            lastKey = key;

            keyGenerator = KeyGenerator.getInstance("DES");

            keyGenerator.init(56);      //指定key长度，同时也是密钥长度(56位)
            SecretKey secretKey = keyGenerator.generateKey(); //生成key的材料
            //生成key

            //key转换成密钥
            DESKeySpec desKeySpec = new DESKeySpec(secretKey.getEncoded());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            key = factory.generateSecret(desKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
