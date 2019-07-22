package com.tryingpfq.proxy.jdk;

/**
 * @Author Tryingpfq
 * @Time 2019/7/22 23:00
 *
 * 以战队作为一个接口
 */
public interface TeamService {

    void apply(long teamId);

    void quict();

    void kick();
}
