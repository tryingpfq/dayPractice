package com.tryingpfq.nettyRpc.register;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class RpcRegistry {

    private int port;

    public RpcRegistry(int port) {
        this.port = port;
    }

    public  void start(){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();

        try {
            b.group(boss, work).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            pipeline.addLast(new LengthFieldPrepender(4));
                            pipeline.addLast("encoder", new ObjectEncoder());
                            pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE,
                                    ClassResolvers.cacheDisabled(null)));

                            pipeline.addLast("registryHandler", new RegistryHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = b.bind(port).sync();
            System.out.println("server listenr is start");
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new RpcRegistry(8888).start();
    }
}
