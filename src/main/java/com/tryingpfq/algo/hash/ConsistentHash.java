package com.tryingpfq.algo.hash;

import java.util.*;

/**
 * @author tryingpfq
 * @date 2020/3/20
 **/
public class ConsistentHash<T>{
    //节点的复制因子 实际节点个数
    private final int numberOfReplices;

    //虚拟节点的hash值
    private final SortedMap<Integer, T> circle = new TreeMap<>();

    public ConsistentHash(int numberOfReplices, Collection<T> nodes) {
        this.numberOfReplices = numberOfReplices;
        for (T node : nodes) {
            addNode(node);
        }
    }

    public void addNode(T node){
        for (int i = 0; i < numberOfReplices; i++) {
            String nodeStr = node.toString() + i;
            int hashCode = nodeStr.hashCode();
            circle.put(hashCode, node);
        }
    }

    public void remove(T node) {
        for (int i = 0; i < numberOfReplices; i++) {
            circle.remove((node.toString() + i).hashCode());
        }
    }

    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = key.hashCode();
        if (!circle.containsKey(hash)) {
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

    public static void main(String[] args) {
        Set<String> nodes = new HashSet<>();
        nodes.add("A");
        nodes.add("B");
        nodes.add("C");

        ConsistentHash<String> consistentHash = new ConsistentHash<String>(2, nodes);
        consistentHash.addNode("D");

        String node =consistentHash.get("apple");
        System.out.println("node----------->:"+node);
    }

}
