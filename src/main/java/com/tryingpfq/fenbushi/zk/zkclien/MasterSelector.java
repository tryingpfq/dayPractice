package com.tryingpfq.fenbushi.zk.zkclien;

import com.tryingpfq.fenbushi.zk.ZkCons;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** 基于ZkClient 实现的Master选举过程
 * @Author tryingpfq
 * @Date 2020/3/28
 */
public class MasterSelector {
    private ZkClient zkClient;

    /**
     * 注册节点内容变化
     */
    private IZkDataListener dataListener;

    private ServerInfo mySelf;

    private ServerInfo master;

    private static boolean isRunning = false;

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public MasterSelector(ZkClient zkClient, ServerInfo serverInfo) {
        this.zkClient = zkClient;
        this.mySelf = serverInfo;

        this.dataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.err.println("server:" + master.getServerId() + " has delete");
                master = null;
                //两秒之后重新进行选举
                executorService.schedule(() ->{
                    chooserMaster();
                },2,TimeUnit.SECONDS);
            }
        };

    }

    public void start(){
        if (!isRunning) {
            isRunning = true;
            System.out.println("server:"+ mySelf.getServerId()+" start");
            //注册节点事件
            zkClient.subscribeDataChanges(ZkCons.MASTER_PATH, dataListener);
            chooserMaster();
        }

    }

    private void chooserMaster(){
        if (!isRunning) {
            System.out.println("server:"+ mySelf.getServerId() + " is stop");
            return;
        }
        try {
            zkClient.createEphemeral(ZkCons.MASTER_PATH,mySelf);
            master = mySelf;
            System.out.println("server:" + master.getServerId() + "成功选举为master");

            //触发故障
            executorService.schedule(() -> {
                releaseMaster();
            }, 5, TimeUnit.SECONDS);
        } catch (RuntimeException e) {
            ServerInfo info = zkClient.readData(ZkCons.MASTER_PATH);
            if (info == null) {
                checkIsMaster();
            }else{
                master = info;
            }
        }

    }

    public void stop(){
        if (isRunning) {
            isRunning = false;
            executorService.shutdownNow();
            zkClient.unsubscribeDataChanges(ZkCons.MASTER_PATH, dataListener);
            releaseMaster();
        }
    }

    /**
     * 模拟master故障
     */
    public void releaseMaster(){
        if (master == null) {
            return;
        }
        if (checkIsMaster()) {
            zkClient.delete(ZkCons.MASTER_PATH);
        }
    }

    public boolean checkIsMaster(){
        if (master == null || mySelf == null) {
            return false;
        }
        return mySelf.getServerId() == master.getServerId();
    }

}
