package com.tryingpfq.netty.rpc.nettyRpc.consumer.proxy;


import com.tryingpfq.netty.rpc.nettyRpc.InvokeMsg;
import com.tryingpfq.netty.rpc.nettyRpc.register.RegistryHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcProxy {


    @SuppressWarnings("unchecked")
    public static <T> T create(Class<?> clazz) {
        MethodProxy proxy = new MethodProxy(clazz);
        T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, proxy);
        return result;
    }

    static class MethodProxy implements InvocationHandler{
        private Class<?> clazz;

        public MethodProxy(Class<?> clazz) {
            this.clazz = clazz;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            }else {
                return rpcInvoke(proxy, method, args);
            }
        }


        private Object rpcInvoke(Object proxy, Method method, Object[] args) {
            InvokeMsg msg = new InvokeMsg();
            msg.setClassName(this.clazz.getName());
            msg.setMethodName(method.getName());
            msg.setParamTypes(method.getParameterTypes());
            msg.setValues(args);

            RpcProxyHandler handler = new RpcProxyHandler();
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group).channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY,true)
                        .handler(new ChannelInitializer<SocketChannel>() {
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
                        });

                ChannelFuture future = bootstrap.connect("localhost", 8888).sync();
                future.channel().writeAndFlush(msg).sync();
                future.channel().closeFuture().sync();
                return handler.getResponse();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                group.shutdownGracefully();
            }
            return null;
        }
    }
}
