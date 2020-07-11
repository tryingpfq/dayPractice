package com.tryingpfq;

import org.apache.poi.ss.formula.ThreeDEval;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
//        System.err.println("start main");
//        try {
//            test();
//        } catch (Exception e) {
//            System.err.println("main catch");
//        }
//
//
//        System.err.println("finish main");

        creteFile1();
        creteFile2();

    }



    private static void creteFile1() {
        FileWriter writer;

        File file = new File("test.txt");
        try {
            writer = new FileWriter(file, true);
            writer.write("abc1");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void creteFile2() {
        FileWriter writer;

        File file = new File("test.txt");
        try {
            writer = new FileWriter(file, true);
            writer.write("abc2");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void test() throws Exception {
        exc0();
        try {

        } catch (Exception e) {
            System.err.println("test catch");
        }

    }

    public static void exc0() {
        try {
            exc(0);
        } catch (Exception e) {
            try {
                throw new Exception("exc");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.err.println("exc 0");
    }

    public static void exc(int i) {
        int a = 10 / i;
        System.out.println(i);
    }
}
