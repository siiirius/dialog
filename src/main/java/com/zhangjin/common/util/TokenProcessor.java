package com.zhangjin.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.MessageDigest;

/**
 * Created by siiiriu on 2020/8/9.
 */
public class TokenProcessor {


    public static final String TOKEN_COOKIE_NAME = "token";


    public static Token decode(String token, SecretKey key) {
        if (key == null) {
            return null;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");  //算法类型/工作方式/填充方式
            cipher.init(Cipher.DECRYPT_MODE, key);


            byte[] bytes = cipher.doFinal(Hex.decodeHex(token));

            return objectMapper.readValue(bytes, Token.class);
        } catch (Exception e) {
            return null;
        }
    }


    public static String encode(Token token, SecretKey key) throws Exception {

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");  //算法类型/工作方式/填充方式
        cipher.init(Cipher.ENCRYPT_MODE, key);

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = objectMapper.writeValueAsBytes(token);


        return Hex.encodeHexString(cipher.doFinal(bytes));
    }


    public static void jdkDES(String src) {
        try {
            //生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);      //指定key长度，同时也是密钥长度(56位)
            SecretKey secretKey = keyGenerator.generateKey(); //生成key的材料
            byte[] key = secretKey.getEncoded();  //生成key

            //key转换成密钥
            DESKeySpec desKeySpec = new DESKeySpec(key);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            SecretKey key2 = factory.generateSecret(desKeySpec);      //转换后的密钥

            //加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");  //算法类型/工作方式/填充方式
            cipher.init(Cipher.ENCRYPT_MODE, key2);   //指定为加密模式
            byte[] result = cipher.doFinal(src.getBytes());
            String s = Hex.encodeHexString(result);
            System.out.println("jdkDES加密: " + s);  //转换为十六进制

            //解密
            cipher.init(Cipher.DECRYPT_MODE, key2);  //相同密钥，指定为解密模式
            result = cipher.doFinal(Hex.decodeHex(s));   //根据加密内容解密
            System.out.println("jdkDES解密: " + new String(result));  //转换字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean checkHash(Token token) throws Exception {

        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(token.getUser().getName().getBytes());
        String hash = Hex.encodeHexString(instance.digest());
        return hash.equals(token.getHash());
    }


    public static void setHash(Token token) throws Exception {
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(token.getUser().getName().getBytes());
        String hash = Hex.encodeHexString(instance.digest());
        token.setHash(hash);
    }

}
