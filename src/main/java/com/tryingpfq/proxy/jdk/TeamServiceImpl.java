package com.tryingpfq.proxy.jdk;

/**
 * @Author Tryingpfq
 * @Time 2019/7/22 23:01
 */
public class TeamServiceImpl implements TeamService {
    @Override
    public void apply(long teamId) {
        System.out.println("apply : " + teamId);
    }

    @Override
    public void quict() {

    }

    @Override
    public void kick() {

    }
}
