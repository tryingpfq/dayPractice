package com.tryingpfq.algo.bit;

/**
 * @Author tryingpfq
 * @Date 2020/4/19
 */
public class BitMap {
    private char[] bytes;

    private int nbits;

    private BitMap(int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits / 16 + 1];
    }

    public void set(int k) {
        if (k > nbits) {
            return;
        }
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        bytes[byteIndex] |= 1 << bitIndex;
    }

    public boolean get(int k) {
        if (k > nbits) {
            return false;
        }
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }


    public static void main(String[] args) {
        int count = 100;
        BitMap bitMap = new BitMap(count);
        bitMap.set(16);
        bitMap.set(17);
        bitMap.set(39);

        System.err.println(bitMap.get(16));
        System.err.println(bitMap.get(20));
    }
}
