package com.tryingpfq.nettyRpc.provider;


import com.tryingpfq.nettyRpc.service.IRpcService;

public class RpcServerImpl implements IRpcService {

    public String rpcMethod(String name) {
        return name;
    }

}
