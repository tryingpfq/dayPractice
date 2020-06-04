package com.tryingpfq.lambda1;

import com.google.inject.internal.cglib.core.$ClassEmitter;
import com.google.inject.internal.cglib.core.$ClassNameReader;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author tryingpfq
 * @date 2020/5/5
 **/
public class Lambda2 {

    public static void main(String[] args) {
        demo();
    }

    public static void demo(){
        new Thread(() ->{
            System.err.println("aa");
            System.err.println("bb");
        }).start();

        List<String> list = Arrays.asList("a", "b", "c","dd");

//        Collections.sort(list,(s1,s2) -> {
//            if (s1.length() > s2.length()) {
//                return 1;
//            }else{
//                return -1;
//            }
//        });

        list.forEach(s -> System.err.println(s));

        ArrayList<String> list1 = new ArrayList<>(list);

        list1.removeIf(s -> s.length() > 1);

        System.err.println(list);


        Map<Integer, String> values = new HashMap<>();
        values.put(1, "aaa");
        values.forEach((k, v) -> System.err.println(k + "_" + v));

        Map<Integer,Set<String>> map = new HashMap<>();
        Set<String> sets = new HashSet<>();
        sets.add("aa");
        map.put(1, sets);
        map.computeIfAbsent(2, v -> new HashSet<>()).add("bb");
        System.err.println(map);

        list.stream();

        Stream<String> stringStream = Stream.of("a", "b");

        Consumer<String> c = s -> System.err.println("a");
    }


}
