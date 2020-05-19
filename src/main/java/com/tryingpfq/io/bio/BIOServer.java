package com.tryingpfq.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
	
	ServerSocket server;

	//服务器
	public BIOServer(int port){
		try {
			server = new ServerSocket(port);
			System.out.println("BIO服务已启动，监听端口是：" + port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 开始监听，并处理逻辑
	 * @throws IOException 
	 */
	public void listener() throws IOException{
		new Thread(() -> {
			Socket client = null;//等待客户端连接，阻塞方法
			while (true) {
				try {
					client = server.accept();
				} catch (IOException e) {
					e.printStackTrace();
				}

				//开始发送一次你好
				OutputStream first = null;
				try {
					first = client.getOutputStream();
					System.out.println(String.format("有客户端连接,和[客户端]:[%s],说你好!",client.getInetAddress()));
					first.write("你好!".getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}

				//BIO为每一条连接建立一条线程管理
				Socket finalClient = client;
				new Thread(() -> {
					Socket socket = finalClient;
					int i = 0;
					while(true){
						try {
							InputStream is = finalClient.getInputStream();

							//缓冲区，数组而已
							byte [] buff = new byte[1024];
							int len = is.read(buff);	//如果没有数据这里会阻塞在这里的
							if(len > 0){
								String msg = new String(buff,0,len);
								System.out.println("服务端收到:" + msg);
							}
							OutputStream outputStream = finalClient.getOutputStream();
							String response = "serverResp_" + i;
							outputStream.write(response.getBytes());
							i++;
						} catch (Exception e) {
						}
					}
				}).start();
			}
		}).start();

	}
	
	
	public static void main(String[] args) throws IOException {
		new BIOServer(8080).listener();
	}
	
}
