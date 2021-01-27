package com.tryingpfq.algo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tryingpfq
 * @date 2021/1/27
 **/
public class LRUCache<K,V> {

    private final int CAPACITY;

    private Map<K, LNode> nodeCache = new HashMap<>();

    private LNode head,tail;

    private int size;

    public LRUCache(int cap) {
        CAPACITY = cap;
        head = new LNode();
        tail = new LNode();
        head.next = tail;
        tail.pre = head;
    }

    public V get(K key) {
        LNode lNode = nodeCache.get(key);
        if (lNode == null) {
            return null;
        }
        moveAndAddToHead(lNode);
        return (V) lNode.value;
    }

    public void put(K key, V value) {
        LNode old = nodeCache.get(key);
        if (old == null) {
            LNode newNode = new LNode(key, value);
            size++;
            addToHead(newNode);
            nodeCache.put(key, newNode);
            if (size > CAPACITY) {
                //移除尾部
                removeLast();
                size--;
            }
        }else{
            old.value = value;
            moveAndAddToHead(old);
        }

    }

    private void removeLast() {
        LNode remover = tail.pre;
        remover.pre.next = tail;
        tail.pre = remover.pre;
        remover.next = null;
        remover.pre = null;
        nodeCache.remove(remover.key);
    }

    private void addToHead(LNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void moveAndAddToHead(LNode node) {
        //先移除，然后加到头部
        node.pre.next = node.next;
        node.next.pre = node.pre;

        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    public class LNode<K,V>{
        private K key;
        private V value;

        private LNode pre;
        private LNode next;

        public LNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public LNode() {

        }
    }
}
