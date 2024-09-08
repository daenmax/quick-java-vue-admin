package cn.daenx.myadmin.common.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

public class AesUtil {
    /**
     * 16字节
     */
    private static String AES_KEY = "aaaa9be5p71e960c1524522f000bc1d7";


    public static void main(String[] args) {
        String content = "12345678";
        String encrypt = encrypt(content);
        System.out.println(encrypt);
        System.out.println(decrypt(encrypt));

//        String t = System.currentTimeMillis() /1000+"";
//        System.out.println(t);
    }

    public static String encrypt(String content) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, AES_KEY.getBytes(), AES_KEY.substring(0, 16).getBytes());
        byte[] encrypt = aes.encrypt(content.getBytes());
        String encode = Base64.encode(encrypt);
        return encode;
    }

    public static String decrypt(String content) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, AES_KEY.getBytes(), AES_KEY.substring(0, 16).getBytes());
        String decrypt = aes.decryptStr(Base64.decode(content));
        return decrypt;
    }

    public static String encrypt(String content, String key) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), key.substring(0, 16).getBytes());
        byte[] encrypt = aes.encrypt(content.getBytes());
        String encode = Base64.encode(encrypt);
        return encode;
    }

    public static String decrypt(String content, String key) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(), key.substring(0, 16).getBytes());
        String decrypt = aes.decryptStr(Base64.decode(content));
        return decrypt;
    }
}
