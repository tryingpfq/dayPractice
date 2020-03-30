package com.tryingpfq.fenbushi.zk.zkclien;

import lombok.Getter;

import java.io.Serializable;

/**
 * @Author tryingpfq
 * @Date 2020/3/28
 */
@Getter
public class ServerInfo implements Serializable {
    private int serverId;

    private String serverName;

    public ServerInfo(int id, String serverName) {
        this.serverId = id;
        this.serverName = serverName;
    }

    @Override
    public String toString() {
        return "ServerInfo{" +
                "serverId=" + serverId +
                ", serverName='" + serverName + '\'' +
                '}';
    }
}
