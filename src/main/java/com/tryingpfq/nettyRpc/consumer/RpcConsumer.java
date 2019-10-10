package com.tryingpfq.nettyRpc.consumer;

import com.tryingpfq.consumer.proxy.RpcProxy;
import com.tryingpfq.service.IRpcService;

public class RpcConsumer {

    public static void main(String[] args) {
        IRpcService service = RpcProxy.create(IRpcService.class);
        String val = service.rpcMethod("hello");
        System.out.println(val);
    }
}
