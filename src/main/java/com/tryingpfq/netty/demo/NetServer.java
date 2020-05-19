package com.tryingpfq.netty.demo;


import com.tryingpfq.netty.demo.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

/**
 * @Author tryingpfq
 * @Date 2020/5/16
 */
public class NetServer {




    public static void main(String[] args) {
        start(3322);
    }

    public static void start(int port) {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        try {
            b.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childAttr(AttributeKey.newInstance("childAttr"), "childAttrValue")
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new InBoundHandlerA());
                            pipeline.addLast(new InBoundHandlerB());
                            pipeline.addLast(new InBoundHandlerC());
                            pipeline.addLast(new OutboundHandlerA());
                            pipeline.addLast(new OutboundHandlerB());
                            pipeline.addLast(new OutboundHandlerC());
                        }
                    });
            ChannelFuture f = b.bind(port).sync();
            System.err.println("服务端已经启动，端口为: " + port);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
