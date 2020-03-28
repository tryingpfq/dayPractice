package com.tryingpfq.fenbushi.zk.lock;

import com.tryingpfq.fenbushi.zk.javademo.ZkCliDemo;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * 基于zookeeper排它锁的实现
 * @Author tryingpfq
 * @Date 2020/3/28
 */
public class LockOne {

    private static final String LOCK_PATH = "/Locks/LockOne";

    private static ZkCliDemo zkCli;

    private static ZooKeeper zk;

    public LockOne(){
        zkCli = new ZkCliDemo();
    }


    public void connect(){
        try {
            zk = zkCli.connectZookeeper();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取锁
     * @return
     */
    public boolean lock(){
        if (zk == null) {
            return false;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            watchNode(countDownLatch);
            countDownLatch.await();
            try {
                doLock();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void doLock() throws Exception {
        zkCli.createTempNode(LOCK_PATH, "123");
    }

    /**
     * 监听
     * @param countDownLatch
     */
    private void watchNode(CountDownLatch countDownLatch){
        try {
            if (zk.exists(LOCK_PATH,true) == null) {
                System.err.println("当前锁节点不存在");
                countDownLatch.countDown();
            }else{
                zk.exists(LOCK_PATH, watchedEvent -> {
                    System.err.println("zkExit watch");
                    if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                        System.err.println("delete Node");
                        countDownLatch.countDown();
                    }
                });
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放锁
     */
    public void unLock(){
        try {
            zkCli.deleteNode(LOCK_PATH);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        new Thread(() ->{

        }).start();
        LockOne lockOne = new LockOne();
        lockOne.connect();
        lockOne.lock();
        lockOne.unLock();
    }
}
