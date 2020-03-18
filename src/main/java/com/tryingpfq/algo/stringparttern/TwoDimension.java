package com.tryingpfq.algo.stringparttern;

/**
 * @author tryingpfq
 * @date 2020/3/16
 **/
public class TwoDimension {

    private static char[][] mainChar = new char[6][6];

    private static char[][] patternChar = new char[2][2];

    static {
        for (int i = 0; i < mainChar.length; i++) {
            for (int j = 0; j < mainChar[i].length; j++) {
                mainChar[i][j] ='a';
            }
        }
    }

}
