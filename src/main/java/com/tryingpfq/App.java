package com.tryingpfq;

import com.tryingpfq.design.singleton.StaticClassSingleton;
import org.apache.poi.ss.formula.ThreeDEval;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        int sum = 0;
//
//        for (int i = 0; i < 1000000; i++) {
//            sum += fn(i);
//        }
//
//        try {
//            Thread.sleep(10000);
//
//            for (int i = 0; i < 1000000; i++) {
//                sum += fn(i);
//            }
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(sum);

        try {
            excep();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("after excep");
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

    public void aa(){


       // PhantomReference
    }


    public static void excep(){
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.err.println(classLoader);

        ClassLoader loader = StaticClassSingleton.class.getClassLoader();
        System.err.println(loader);
    }

    public static void tryWithResource() {
        try (InputStream inputStream = new FileInputStream("A")) {
            inputStream.read();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
