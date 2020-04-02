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
        new InnerClassDemo().innerClass(player);
        player.playeDoInner();
    }

    public static void hideInnerClass(Player player,List<String> message) {
      player.addExecutor(new IExecutor() {
          @Override
          public void executor(Player player) {
              System.err.println(message);
          }
      });
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
