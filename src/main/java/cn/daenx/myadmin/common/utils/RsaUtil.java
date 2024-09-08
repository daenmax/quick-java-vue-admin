package cn.daenx.myadmin.common.utils;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

public class RsaUtil {

    public static void main(String[] args) {
        String content = "你好阿萨德按时大声地\n阿萨德654g65j4";
        String publickeyobj = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDfrb0eh8Y8ze1/Z9Ja63NBBK+G6mmi4AEEnaruyNu5mZcZ0nOsjYaNqqqB0v471Y9yobODrTKvmt82yTzyO0NA5sojW2KqIhW0/s6Ai4yemHScrKSE2xckNux7di77wATzola4uPl8F+x6Ya8TJ2qC773eihrgM7a7IZcLKGttFQIDAQAB";
        String privatekeyobj = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAN+tvR6HxjzN7X9n0lrrc0EEr4bqaaLgAQSdqu7I27mZlxnSc6yNho2qqoHS/jvVj3Khs4OtMq+a3zbJPPI7Q0DmyiNbYqoiFbT+zoCLjJ6YdJyspITbFyQ27Ht2LvvABPOiVri4+XwX7HphrxMnaoLvvd6KGuAztrshlwsoa20VAgMBAAECgYBWZTOKktPpkXq9/rp73rymLOqlWG6T+CS8hS/MSm3AKwh3c1e1mvaRSo3QVlBXiR3+n2GIp0HQHShBb4ooX+aR9o6jDkA5IvqvuM2y1yxBoJloI8hclK4V4fFZjf4TjFEOpZlCdn9d6qtVmcQ9LKEYzsr5uaZRlfDGXW1j4Kya8wJBAPP2U3BF/tvfFs6o2Y8u2GKtq9mQk+UEt4N8ZUPGlTzPgI3lpxkv58u/R6IYgz23CgRMkD5oLpYk9smR7LBTRPMCQQDqtzIfoxRT9DKUMUk6UZRsoXLgy+GUIl4Oh6uxCJukqYDy0Pi1Lv7A66rKc4knUBiG63Z4Ev/O2OexQZFF9qfXAkAIQp/inkwZz7zq9GahZXmoJa86551KVNWN2ylmmRO2gmQc6di1jpyvlmNrhp7y+P6a1KjRpB9nsAq3GnzEH52nAkEAyWiMRWOeNPBuWoX0bWvFrnXX6xhoqMHY7iTzaoWVcXXWZgZ19JuwFYHk27bKsYZklZoF3cGH60prfsphy6R9nwJAO/kQBQDVavATSkYnyuiapflAf8RKlgDaEVo99n91IZ+D1Vvz6rjzJ21gZwjMTv4f5iyjb1ia+nFWGFvghjL/dA==";
        String encrypt = encrypt(content, publickeyobj);
        System.out.println("加密后:" + encrypt);
        System.out.println("解密后:" + decrypt(encrypt, privatekeyobj));
    }

    public static void genKey() {
        RSA rsa = new RSA();
        //获得私钥
        rsa.getPrivateKey();
        rsa.getPrivateKeyBase64();
        //获得公钥
        rsa.getPublicKey();
        rsa.getPublicKeyBase64();
        System.out.println("公钥:" + rsa.getPublicKeyBase64());
        System.out.println("私钥:" + rsa.getPrivateKeyBase64());
    }

    public static String decrypt(String content, String privateKey) {
        RSA rsa = new RSA(privateKey, null);
        String res = rsa.decryptStr(content, KeyType.PrivateKey);
        return res;
    }

    public static String encrypt(String content, String publicKey) {
        RSA rsa = new RSA(null, publicKey);
        String res = rsa.encryptHex(content, KeyType.PublicKey);
        return res;
    }
}
