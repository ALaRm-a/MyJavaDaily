package com.zhong.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * MD5加密工具类
 * <p>
 * 提供MD5加密（哈希）、加盐加密、以及校验方法。
 * 注意：MD5是一种单向散列算法（Hash），理论上是<b>不可逆</b>的，因此无法进行“解密”。
 * 所谓的“解密”通常是指通过彩虹表暴力破解，或者比对已知Hash值。
 * 本工具类提供了{@code verify}方法用于校验明文和密文是否匹配。
 * </p>
 *
 * @author Opencode
 * @version 1.0
 */
public class MD5Util {

    // 十六进制字符数组
    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

    /**
     * 普通MD5加密
     *
     * @param input 需要加密的字符串
     * @return 32位小写16进制MD5字符串，如果输入为null则返回null
     */
    public static String encrypt(String input) {
        if (input == null) {
            return null;
        }
        try {
            // 获取MD5摘要算法的 MessageDigest 对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            md.update(input.getBytes());
            // 获得密文
            byte[] digest = md.digest();
            // 将字节数组转换为16进制字符串
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 带盐值的MD5加密
     * <p>
     * 通过混入随机字符串（盐值）来增加破解难度。
     * 通常建议将生成的salt和最终的密文一起保存，或者使用固定的盐值。
     * </p>
     *
     * @param input 需要加密的字符串
     * @param salt  盐值
     * @return 加盐后的MD5字符串
     */
    public static String encrypt(String input, String salt) {
        if (input == null) {
            return null;
        }
        if (salt == null) {
            salt = "";
        }
        // 简单的加盐方式：直接拼接，也可以采用更复杂的混合方式
        return encrypt(input + salt);
    }

    /**
     * 校验普通MD5
     *
     * @param input   待验证的明文
     * @param md5Hash 已知的MD5密文
     * @return true if 匹配, else false
     */
    public static boolean verify(String input, String md5Hash) {
        if (input == null || md5Hash == null) {
            return false;
        }
        String calculated = encrypt(input);
        return calculated != null && calculated.equalsIgnoreCase(md5Hash);
    }

    /**
     * 校验加盐MD5
     *
     * @param input   待验证的明文
     * @param salt    加密时使用的盐值
     * @param md5Hash 已知的MD5密文
     * @return true if 匹配, else false
     */
    public static boolean verify(String input, String salt, String md5Hash) {
        if (input == null || md5Hash == null) {
            return false;
        }
        String calculated = encrypt(input, salt);
        return calculated != null && calculated.equalsIgnoreCase(md5Hash);
    }

    /**
     * 生成一个随机盐值
     *
     * @return 随机UUID作为盐值
     */
    public static String generateSalt() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    /**
     * 将字节数组转换为16进制字符串
     *
     * @param bytes 字节数组
     * @return 16进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            // 将字节的高4位和低4位分别转换为对应的16进制字符
            sb.append(HEX_DIGITS[(b >> 4) & 0x0f]);
            sb.append(HEX_DIGITS[b & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * 演示如何使用本工具类
     */

    /*
    public static void main(String[] args) {
        System.out.println("=== MD5Util 使用演示 ===");

        String password = "mySecretPassword123";
        System.out.println("原始密码: " + password);

        // 1. 普通MD5加密
        String md5 = MD5Util.encrypt(password);
        System.out.println("\n[普通MD5]");
        System.out.println("密文: " + md5);
        System.out.println("校验(正确): " + MD5Util.verify(password, md5));
        System.out.println("校验(错误): " + MD5Util.verify("wrongPassword", md5));

        // 2. 加盐MD5加密
        String salt = MD5Util.generateSalt();
        String saltedMd5 = MD5Util.encrypt(password, salt);
        System.out.println("\n[加盐MD5]");
        System.out.println("盐值: " + salt);
        System.out.println("密文: " + saltedMd5);
        System.out.println("校验(正确): " + MD5Util.verify(password, salt, saltedMd5));
        System.out.println("校验(错误): " + MD5Util.verify(password, "wrongSalt", saltedMd5));
    }
    */

}
