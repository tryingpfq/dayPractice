package com.tryingpfq.proxy.cglib;

/**
 * @author tryingpfq
 * @date 2019/7/23 14:27
 */
public class TeamService {

    public TeamService(){
        System.out.println("teamService construct");
    }

    public final void accept(long teamId){
        System.out.println("accept " + teamId);
    }

    public void apply(){
        System.out.println("apply");
    }

    public void quik(){
        System.out.println("quick");
    }
}
