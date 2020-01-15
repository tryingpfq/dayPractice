package com.tryingpfq;

import org.apache.poi.ss.formula.ThreeDEval;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int sum = 0;

        for (int i = 0; i < 1000000; i++) {
            sum += fn(i);
        }

        try {
            Thread.sleep(10000);

            for (int i = 0; i < 1000000; i++) {
                sum += fn(i);
            }
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }

    public static int fn(int id) {
        User user = new User(id);
        return user.getId();
    }

    static class  User{
        private int id;

        private String name;

        public User(int id){
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
}
