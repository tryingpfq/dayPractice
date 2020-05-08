package com.tryingpfq.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class BIOClient {

	public static void main(String[] args) throws IOException, InterruptedException {
		final Socket client = new Socket("localhost", 8080);
			//写线程
			new Thread(() -> {
				try{
					//latch.await();
					OutputStream outputStream = client.getOutputStream();
					byte[] buffer = new byte[1024];
					int n;
					while ((n = System.in.read(buffer)) > 0) {
						outputStream.write(buffer, 0, n);
					}
				}catch(Exception e){
				}
			}).start();
			//读线程
			new Thread(() -> {
				try {
					InputStream in = client.getInputStream();
					byte[] buffer = new byte[1024];
					int n;
					while ((n = in.read(buffer)) > 0) {
						//
						System.out.print("客户端收到消息：");
						System.out.write(buffer, 0, n);
						System.out.println();
						System.out.println("客户端请输入：");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}).start();
			Thread.sleep(Integer.MAX_VALUE);
	}
}
