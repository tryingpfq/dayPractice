package com.tryingpfq.io.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author Tryingpfq
 * @Time 2019/9/21 20:06
 */
public class NIOServer {

    private static Charset charset = Charset.forName("UTF-8");
    //用来记录在线人数，以及昵称
    private static HashSet<String> users = new HashSet<String>();

    private static String USER_EXIST = "系统提示：该昵称已经存在，请换一个昵称";
    //相当于自定义协议格式，与客户端协商好
    private static String USER_CONTENT_SPILIT = "#@#";

    private static Selector selector = null;

    public static void main(String[] args) {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();

            server.bind(new InetSocketAddress(8080));
            server.configureBlocking(false);

            selector = Selector.open();

            server.register(selector, SelectionKey.OP_ACCEPT);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listener(){
        while (true) {
            try {
                int wait = selector.select();

                if (wait == 0) {
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    process(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void process(SelectionKey key) {
        if (key.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            try {
                SocketChannel client = serverSocketChannel.accept();

                client.configureBlocking(false);

                client.register(selector, SelectionKey.OP_READ);

                key.interestOps(SelectionKey.OP_ACCEPT);
//            System.out.println("有客户端连接，IP地址为 :" + sc.getRemoteAddress());
                client.write(charset.encode("请输入你的昵称"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //处理来自客户端的数据读取请求
        if(key.isReadable()){
            //返回该SelectionKey对应的 Channel，其中有数据需要读取
            SocketChannel client = (SocketChannel)key.channel();

            //往缓冲区读数据
            ByteBuffer buff = ByteBuffer.allocate(1024);
            StringBuilder content = new StringBuilder();
            try{
                while(client.read(buff) > 0)
                {
                    buff.flip();
                    content.append(charset.decode(buff));

                }
//                System.out.println("从IP地址为：" + sc.getRemoteAddress() + "的获取到消息: " + content);
                //将此对应的channel设置为准备下一次接受数据
                key.interestOps(SelectionKey.OP_READ);
            }catch (IOException io){
                key.cancel();
                if(key.channel() != null)
                {
                    try {
                        key.channel().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(content.length() > 0) {
                String[] arrayContent = content.toString().split(USER_CONTENT_SPILIT);
                //注册用户
                if(arrayContent != null && arrayContent.length == 1) {
                    String name = arrayContent[0];
                    if(users.contains(name)) {
                        try {
                            client.write(charset.encode(USER_EXIST));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        users.add(name);
                        int onlineCount = onlineCount();
                        String message = "欢迎 " + name + " 进入聊天室! 当前在线人数:" + onlineCount;
                        broadCast(null, message);
                    }
                }
                //注册完了，发送消息
                else if(arrayContent != null && arrayContent.length > 1) {
                    String name = arrayContent[0];
                    String message = content.substring(name.length() + USER_CONTENT_SPILIT.length());
                    message = name + " 说 " + message;
                    if(users.contains(name)) {
                        //不回发给发送此内容的客户端
                        broadCast(client, message);
                    }
                }
            }
        }
    }

    private static int onlineCount() {
        int res = 0;
        for(SelectionKey key : selector.keys()){
            Channel target = key.channel();

            if(target instanceof SocketChannel){
                res++;
            }
        }
        return res;
    }

    private static void broadCast(SocketChannel client, String content)  {
        //广播数据到所有的SocketChannel中
        for(SelectionKey key : selector.keys()) {
            Channel targetchannel = key.channel();
            //如果client不为空，不回发给发送此内容的客户端
            if(targetchannel instanceof SocketChannel && targetchannel != client) {
                SocketChannel target = (SocketChannel)targetchannel;
                try {
                    target.write(charset.encode(content));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
