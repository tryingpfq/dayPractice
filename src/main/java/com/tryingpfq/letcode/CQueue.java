package com.tryingpfq.letcode;

import java.util.Stack;

/**
 * 栈实现队列
 */
public class CQueue {

    private Stack<Integer> stack1;

    private Stack<Integer> stack2;

    public CQueue(){
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    public void pushTail(int v){
        stack1.push(v);
    }

    public int deleteHead(){
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if(stack2.isEmpty()){
            return -1;
        }else{
            int del = stack2.pop();
            return del;
        }
    }
}
