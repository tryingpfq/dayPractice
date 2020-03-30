package com.tryingpfq.fenbushi.zk.javademo;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 基于java Api
 * @Author tryingpfq
 * @Date 2020/3/25
 */
public class ZkCliDemo implements Watcher {
    private static String CONNECT_ADDR = "192.168.146.128:2181,192.168.146.129:2181,192.168.146.130:2181";

    public static int SESSION_TIME_OUT = 20000;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            System.out.println("Watch received event");
            countDownLatch.countDown();
        }
    }

    /**
     * 连接zookeeper
     */
    public ZooKeeper connectZookeeper() throws Exception{
        zooKeeper = new ZooKeeper(CONNECT_ADDR, SESSION_TIME_OUT, this);
        countDownLatch.await();
        System.out.println("zookeeper connection success");
        return zooKeeper;
    }

    /**
     * 创建节点
     * @param path
     * @param data
     * @return
     * @throws Exception
     */
    public String createPerNode(String path,String data) throws Exception{
         return this.zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public String createTempNode(String path,String data) throws Exception{
        return this.zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
    }

    public String createTempSeqNode(String path, String data) throws KeeperException, InterruptedException {
        return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    /**
     * 获取孩子节点
     */
    public List<String> getChilds(String path) throws KeeperException, InterruptedException {
        return zooKeeper.getChildren(path, true);
    }

    public void deleteNode(String path) throws InterruptedException, KeeperException{
        zooKeeper.delete(path, -1);
    }

}
