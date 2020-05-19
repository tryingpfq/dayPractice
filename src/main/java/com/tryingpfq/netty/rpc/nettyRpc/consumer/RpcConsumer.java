package com.tryingpfq.netty.rpc.nettyRpc.consumer;

import com.tryingpfq.netty.rpc.nettyRpc.consumer.proxy.RpcProxy;
import com.tryingpfq.netty.rpc.nettyRpc.service.IRpcService;

public class RpcConsumer {

    public static void main(String[] args) {
        IRpcService service = RpcProxy.create(IRpcService.class);
        String val = service.rpcMethod("hello");
        System.out.println(val);
    }
}
