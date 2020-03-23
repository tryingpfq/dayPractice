package com.tryingpfq.character;

import java.io.UnsupportedEncodingException;

/**
 * @author tryingpfq
 * @date 2020/3/18
 **/
public class CharacterTest {

    public static void main(String[] args) {
//        String str = "中";
//        char x = '中';
//        byte[] bytes = null;
//        byte[] bytes1 = null;
//
//        try {
//            bytes = str.getBytes("utf-8");
//            bytes1 = charToByte(x);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        System.out.println("bytes 大小：" + bytes.length);
//        System.out.println("bytes1大小：" + bytes1.length);
        test1();
    }


    public static void test(){
        char[] array = new char[]{'*'};
        System.err.println(Integer.toHexString(array[0]));

    }

    public static void test1(){
        //平时常见的汉字
        String str = "中";
        //str.length 就是char[]数组长度
        System.err.println(str.length());

        // Unicode编码 汉字扩展 '𠀀' 字
        String str1 = "\uD840\uDC00";
        System.err.println(str1.length());

        char[] array = new char[]{'\uD840', '\uDC00'};
        System.err.println(new String(array));

    }
    public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

}
