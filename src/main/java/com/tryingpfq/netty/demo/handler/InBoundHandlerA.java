package com.tryingpfq.netty.demo.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author tryingpfq
 * @Date 2020/5/16
 */
public class InBoundHandlerA extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive-A");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead-A:" + msg);

        ctx.fireChannelRead(msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("exceptionCaught in A");
        //不处理，继续往下传播
        ctx.fireExceptionCaught(cause);
    }
}
