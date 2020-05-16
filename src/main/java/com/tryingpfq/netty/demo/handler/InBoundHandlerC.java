package com.tryingpfq.netty.demo.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author tryingpfq
 * @Date 2020/5/16
 */
public class InBoundHandlerC extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive-C");
        super.channelActive(ctx);
        // 这样是从当前节点进行传播
       // ctx.fireChannelRead("hello");

        //从头部开始传播
       ctx.pipeline().fireChannelRead("hello");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead-C:" + msg);

        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("exceptionCaught in C");
        //不处理，继续往下传播
        ctx.fireExceptionCaught(cause);
    }
}
