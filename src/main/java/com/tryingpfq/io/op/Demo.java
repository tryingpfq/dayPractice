package com.tryingpfq.io.op;

import io.netty.channel.SingleThreadEventLoop;
import io.netty.util.concurrent.Future;

import java.io.*;

/**
 * @Author tryingpfq
 * @Date 2020/5/1
 */
public class Demo {

    private static String path = "test.txt";

    public static void main(String[] args) throws Exception {
        //read();
        write();
    }

    public static void read() throws Exception {
        FileInputStream inputStream = new FileInputStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String str = reader.readLine();
    }

    public static void write() throws Exception{
        FileOutputStream outputStream = new FileOutputStream(path);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("add");

        bufferedWriter.newLine();
    }
}
