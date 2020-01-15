package com.tryingpfq.lambda1;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class MainLambda {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("test");
        });

        List<Integer> lists = Lists.newArrayList();

        lists.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 3;
            }
        });

        lists.removeIf(a -> a > 1);

        lists.replaceAll(new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                if (integer > 3) {
                    return  0;
                }
                return -1;
            }
        });

        lists.replaceAll(a -> {
            if (a > 3) {
                return 0;
            }
            return -1;
        });

        lists.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });

        lists.sort((a, b) -> a - b);

        HashMap<Integer, String> map = new HashMap<>();
        map.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer integer, String s) {

            }
        });

        map.forEach((k,v) -> {
            System.out.println();
        });

    }




}
