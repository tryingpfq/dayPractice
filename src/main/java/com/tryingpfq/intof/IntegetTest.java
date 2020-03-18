package com.tryingpfq.intof;

/**
 *  主要用来测试Integet对象的缓存 通过参数修改缓存区间
 *  //方法一：-Djava.lang.Integer.IntegerCache.high=255
 *  //方法二：-XX:AutoBoxCacheMax=255
 * @author tryingpfq
 * @date 2020/3/12
 **/
public class IntegetTest {

    public static void main(String[] args) {
        Integer i = 200;
    }

    /**
     * 值得注意的是
     * Integer a = new Integer(123); 这里是不会内享元模式的 也就是IntegerCache
     * Integer a = 123;
     * Integer a = Integer.valueOf(123);
     */
}
