package com.tryingpfq.fenbushi.zk.lock;

import com.tryingpfq.fenbushi.zk.javademo.ZkCliDemo;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/** 基于Java API 共享锁的实现
 * @Author tryingpfq
 * @Date 2020/3/28
 */
public class LockTwo {
    private static final String LOCK_PATH = "/Locks/";

    private static ZkCliDemo zkCli;

    private static ZooKeeper zk;

    private String lockID;

    private CountDownLatch countDownLatch = new CountDownLatch(1);
    public LockTwo(){
        zkCli = new ZkCliDemo();
    }

    public void connect(){
        try {
            zk = zkCli.connectZookeeper();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean lock(){
        try {
            lockID = zkCli.createTempSeqNode(LOCK_PATH, "12");
            System.err.println(Thread.currentThread().getName()+ "-> 成功创建,lock节点[" +lockID+ "]"+"开始竞争锁");
            List<String> childNodes = zkCli.getChilds("/Locks");
            //排序
            SortedSet<String> sortedSet = new TreeSet<>();
            for (String str : childNodes) {
                sortedSet.add(LOCK_PATH + str);
            }
            String first = sortedSet.first();
            if(lockID.equals(first)){
                //表示成功获取锁
                System.err.println(Thread.currentThread().getName()+ "-> 成功获取锁,lock节点[" +lockID+ "]");
                return true;
            }
            //比当前节点更小的节点集合
            SortedSet<String> lessNodes = sortedSet.headSet(first);
            if (!lessNodes.isEmpty()) {
                String preLockId = lessNodes.last();
                zk.exists(preLockId,watchedEvent -> {
                    if (watchedEvent.getType() == Watcher.Event.EventType.NodeDeleted) {
                        countDownLatch.countDown();
                    }
                });
                countDownLatch.await(ZkCliDemo.SESSION_TIME_OUT, TimeUnit.MILLISECONDS);
                System.err.println(Thread.currentThread().getName()+ "成功获取锁:[" +lockID+ "]");
            }
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void unlock(){
        System.err.println(Thread.currentThread().getName()+ "开始释放锁:[" +lockID+ "]");
        try {
            zkCli.deleteNode(lockID);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                LockTwo lockTwo = new LockTwo();
                lockTwo.connect();
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                    lockTwo.lock();
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lockTwo.unlock();
                }
            }).start();
        }
    }
}
