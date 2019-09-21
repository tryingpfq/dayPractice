package com.tryingpfq.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Tryingpfq
 * @Time 2019/9/21 15:42
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            listener(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void listener(ServerSocket serverSocket ){
        while (true) {
            try {
                Socket accept = serverSocket.accept();//等待客户端连接 阻塞方法

                InputStream is = accept.getInputStream();

                byte[] buff = new byte[1024];

                int len = 0;
                while ((len = is.read(buff)) != -1) {
                   String str = new String(buff,0,len);
                   System.out.println("inputStream: " + str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
