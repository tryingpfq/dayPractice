package com.tryingpfq.fenbushi.zk.demo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.apache.zookeeper.Watcher.Event.KeeperState.SyncConnected;

/**
 * @author tryingpfq
 * @date 2020/3/26
 **/
public class ClienDemo implements Watcher {
    public static ZooKeeper zookeeper;

    private static final String Addr = "10.9.12.211:2181";

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == SyncConnected) {
            System.err.println("watch received event");
            countDownLatch.countDown();
        }
    }

    /**
     * 连接zookeeper
     */
    public  void connectZookeeper()  {
        try {
            zookeeper = new ZooKeeper(Addr, 2000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.err.println("zk connection success");
    }

    /**
     * 创建节点
     */
    public String createNode(String path, String data) {
        try {
            return zookeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<String> getChildren(String path) {
        List<String> children = new ArrayList<>();
        try {
             children = zookeeper.getChildren(path, false);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return children;
    }

    /**
     * 获取节点数据
     */
    public String getNodeData(String path) {
        byte[] data = new byte[0];
        try {
            data = zookeeper.getData(path, false, null);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (data == null) {
            return "";
        }
        return new String(data);
    }

    /**
     * 设置节点信息
     */
    public Stat setData(String path, String data) {
        Stat stat = null;
        try {
            stat = zookeeper.setData(path, data.getBytes(), -1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return stat;
    }

    public void deleteNode(String path) {
        try {
            zookeeper.delete(path, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws KeeperException, InterruptedException {
        ClienDemo watcher = new ClienDemo();
        watcher.connectZookeeper();
        List<String> children = zookeeper.getChildren("/dubbo-dev/com.tryingpfq.provider.user.service.IUserService/providers", false);
        Thread.sleep(20000L);

    }
}
