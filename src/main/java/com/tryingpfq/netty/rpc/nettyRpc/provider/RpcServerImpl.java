package com.tryingpfq.netty.rpc.nettyRpc.provider;


import com.tryingpfq.netty.rpc.nettyRpc.service.IRpcService;

public class RpcServerImpl implements IRpcService {

    public String rpcMethod(String name) {
        return name;
    }

}
