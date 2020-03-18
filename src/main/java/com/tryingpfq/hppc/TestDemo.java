package com.tryingpfq.hppc;

import com.carrotsearch.hppc.IntArrayList;
import com.carrotsearch.hppc.IntIntHashMap;
import org.springframework.scripting.groovy.GroovyScriptFactory;

import java.util.ArrayList;
import java.util.List;

public class TestDemo {

    public static void main(String[] args) {
        list();
    }

    public static void list(){

        List list = new ArrayList();
        IntArrayList hppcList = new IntArrayList();
        hppcList.add(1);
        System.out.println(hppcList.get(0));

        IntIntHashMap map = new IntIntHashMap();


        map.put(1, 1);
        map.put(2, 2);
        System.out.println(map);

        System.out.println(map.get(2));
    }
}
