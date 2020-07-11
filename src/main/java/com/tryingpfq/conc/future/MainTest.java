package com.tryingpfq.conc.future;

import java.util.Collections;
import java.util.Map;

/**
 * @Author Tryingpfq
 * @Time 2019/8/4 12:15
 */
public class MainTest {

    public static void main(String[] args) {
        Map<String, Integer> aaa = Collections.singletonMap("aaa", 111);

//        TaskServiceImpl<String,String> taskService = new TaskServiceImpl<String,String>();
//
//        MyCarTask<String,String> myCarTask = new MyCarTask<String,String>();
//
//        Future<?> future = taskService.submit(myCarTask,"car");
//
//        String result = (String) future.get();
//
//        System.out.println(result);
    }
}
