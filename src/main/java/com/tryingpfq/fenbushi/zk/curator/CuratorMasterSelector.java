package com.tryingpfq.fenbushi.zk.curator;

import com.tryingpfq.fenbushi.zk.ZkCons;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.CuratorTempFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 基于curator 工具的Master选举
 * @Author tryingpfq
 * @Date 2020/3/28
 */
public class CuratorMasterSelector {

    public void chooser(){
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(ZkCons.CONNECT_STR)
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework, ZkCons.MASTER_PATH, new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                System.err.println("获得leader 成功");
            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

            }
        });
        leaderSelector.autoRequeue();
        //如果选举成功的话，会到上面takeLeadership中去
        leaderSelector.start();
    }
}
