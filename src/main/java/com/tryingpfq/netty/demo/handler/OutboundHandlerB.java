package com.tryingpfq.netty.demo.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.util.concurrent.TimeUnit;

/**
 * @Author tryingpfq
 * @Date 2020/5/16
 */
public class OutboundHandlerB extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutboundHanderlB->" + msg);
        super.write(ctx, msg, promise);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("outB add and sendMessage");
//        ctx.executor().schedule(() -> {
//            //同样这里是从pipelie开始传播，而不是当前节点
//            ctx.pipeline().write("hello Message");
//        },5, TimeUnit.SECONDS);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("exceptionCaught out B");
        //不处理，继续往下传播
        ctx.fireExceptionCaught(cause);
    }

}
