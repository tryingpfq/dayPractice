package com.tryingpfq.netty.demo.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author tryingpfq
 * @Date 2020/5/16
 */
public class InBoundHandlerB extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive-B");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 假设在这里抛一个异常 没有捕获
        throw new Exception("test exception");
        //System.out.println("channelRead-B:" + msg);
        //ctx.fireChannelRead(msg);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("exceptionCaught in B");
        //不处理，继续往下传播
        ctx.fireExceptionCaught(cause);
    }

}
