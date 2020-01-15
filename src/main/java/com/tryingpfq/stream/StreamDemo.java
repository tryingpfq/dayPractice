package com.tryingpfq.stream;

import java.io.Serializable;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamDemo implements Serializable {

    public static final List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

    public static final List<Integer> ints = Arrays.asList(1, 2, 3, 5, 23, 34);


    public static void main(String[] args) {
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);
        filtered.forEach(s -> {
            System.out.println("this is " + s);
        });

        String joins = strings.stream().filter(s -> !s.isEmpty()).collect(Collectors.joining(","));
        System.out.println(joins);
        Random random = new Random();
       // random.ints().limit(10).forEach(System.out :: println);

        List<Integer> val = ints.stream().map(integer -> integer * integer).distinct().collect(Collectors.toList());
        System.out.println(val);

        List<String>  strings1 = strings.stream().filter(s -> s.isEmpty()).collect(Collectors.toList());
        System.out.println(strings1);

      //  random.ints().limit(10).sorted().forEach(System.out :: println);
        IntSummaryStatistics summaryStatistics = ints.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println(summaryStatistics.getMax());
    }


}
