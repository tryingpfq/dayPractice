package com.tryingpfq.hppc;

import com.carrotsearch.hppc.IntArrayList;

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

    }
}
