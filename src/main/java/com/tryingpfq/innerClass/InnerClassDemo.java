package com.tryingpfq.innerClass;

import org.apache.commons.compress.utils.Lists;
import org.aspectj.apache.bcel.util.Play;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2020/4/2
 **/
public class InnerClassDemo {

    public static void main(String[] args) {
        Player player = new Player();
//        new InnerClassDemo().innerClass(player);
//        player.playeDoInner();
        List<String> strings = Lists.newArrayList();
        strings.add("first");
        hideInnerClass(player,strings);
        player.doExecuror();
        strings.add("second");
        player.doExecuror();
    }

    public static void hideInnerClass(Player player,final List<String> message) {
      player.addExecutor(player1 -> System.err.println(message));
    }

    public void innerClass(Player player) {
        player.setMyInnerClass(new MyInnerClass());
    }

    public void outMethod(){
        System.err.println("outMethod");
    }

    public class MyInnerClass{
        public void doInner(){
            outMethod();
        }
    }
}
