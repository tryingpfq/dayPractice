package com.tryingpfq.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @Author Tryingpfq
 * @Time 2019/9/21 16:12
 */
public class Client {

    public static void main(String[] args) {
        int count = 10;

        final CountDownLatch downLatch = new CountDownLatch(count);

        for (int i = 0; i < count; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        downLatch.await();

                        Socket client = new Socket("localhost", 8080);

                        OutputStream os = client.getOutputStream();

                        String name = UUID.randomUUID().toString();

                        os.write(name.getBytes());
                        os.close();
                        client.close();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
            downLatch.countDown();
        }
        System.out.println("finish");

    }
}
