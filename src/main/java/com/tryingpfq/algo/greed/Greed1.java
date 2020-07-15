package com.tryingpfq.algo.greed;

import com.google.inject.internal.asm.$Type;
import sun.tools.jstat.Jstat;

import java.util.Stack;

/**
 * 题目描述
 *
 * <p>在一个非负整数 a 中，我们希望从中移除 k 个数字，让剩下的数字值最小，如何选择移除哪 k 个数字呢？
 *
 * @author tryingpfq
 * @date 2020/7/15
 **/
public class Greed1 {


    /**
     * 从高位开始，和地位比较，如果地位数字比高位大，则移除
     *
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        if (num == null || num.length() < k) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        stack.push(num.charAt(0));

        for (int i = 1; i < num.length(); i++) {
            char curChar = num.charAt(i);
            while (!stack.empty() && k > 0 && stack.peek() > curChar) {
                k--;
                stack.pop();
            }
            stack.push(curChar);
        }
        // k 有剩余，也就是后面是一个单调递增的序列
        while (k > 0 && !stack.isEmpty()) {
            k--;
            stack.pop();
        }

        //还需要处理一个问题，就是加入first剩下的是0
        while (!stack.isEmpty() && stack.firstElement() == '0') {
            stack.removeElementAt(0);
        }
        if (stack.isEmpty()) {
            return "0";
        }
        char[] arr = new char[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            arr[i] = stack.get(i);
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        String num = "12345264";
        System.out.println(removeKdigits("112", 1));
    }
}
