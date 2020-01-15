package com.tryingpfq.lambda1;

public class MainAnonymousClass {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("test");
            }
        });
    }
}
