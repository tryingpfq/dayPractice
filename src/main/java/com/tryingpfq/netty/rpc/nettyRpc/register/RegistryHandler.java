package com.tryingpfq.netty.rpc.nettyRpc.register;

import com.tryingpfq.netty.rpc.nettyRpc.InvokeMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RegistryHandler extends ChannelInboundHandlerAdapter {
    private static final String packetName = "com.tryingpfq.provider";

    public static ConcurrentHashMap<String, Object> classMap = new ConcurrentHashMap<String, Object>();

    private List<String> classCache = new ArrayList<String>();

    public RegistryHandler() {
        scannerClass(packetName);
        doRegister();
    }

    private void scannerClass(String path){
        URL url = RegistryHandler.class.getClassLoader().getResource(packetName.replaceAll("\\.", "/"));
        File dirFile = new File(url.getFile());
        for (File file : dirFile.listFiles()) {
            if (file.isDirectory()) {
                scannerClass(packetName + "." + file.getName());
            } else {
                classCache.add(packetName + "." + file.getName().replace(".class", ""));
            }
        }
    }

    private void doRegister() {
        if (classCache.isEmpty()) {
            return;
        }
        for (String name : classCache) {
            try {
                Class<?> clazz = Class.forName(name);
                Class<?> interfaces = clazz.getInterfaces()[0];
                classMap.put(interfaces.getName(), clazz.newInstance());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("registryHandler");
        Object result = new Object();
        InvokeMsg request = (InvokeMsg) msg;

        if (classMap.containsKey(request.getClassName())) {
            Object clazz = classMap.get(request.getClassName());

            Method method = clazz.getClass().getMethod(request.getMethodName(), request.getParamTypes());

            result = method.invoke(clazz, request.getValues());

            ctx.writeAndFlush(result);
            ctx.close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.flush();
        ctx.close();
    }
}
