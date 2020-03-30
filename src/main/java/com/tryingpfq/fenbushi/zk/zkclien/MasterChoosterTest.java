package com.tryingpfq.fenbushi.zk.zkclien;

import com.tryingpfq.fenbushi.zk.ZkCons;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;

/**
 * @Author tryingpfq
 * @Date 2020/3/28
 */
public class MasterChoosterTest {

    /**
     * 需要多模拟几个进程执行 就能看出master故障后重新选举的master
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient(ZkCons.CONNECT_STR, ZkCons.SESSION_TIME_OUT, ZkCons.CONN_TIME_OUT);

        ServerInfo serverInfo = new ServerInfo(1, "try_" + 1);

        MasterSelector selector = new MasterSelector(zkClient, serverInfo);
        selector.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.sleep(50000);
    }
}
