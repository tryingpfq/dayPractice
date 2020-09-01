package com.tryingpfq.algo.skillist;

import java.util.Random;

/**
 * 跳表
 *
 * @author tryingpfq
 * @date 2020/9/1
 **/
public class SkipList {

    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;

    private Random r = new Random();
    /**
     * 带头链表
     **/
    private Node head = new Node(MAX_LEVEL);

    public Node find(int value) {
        Node p = head;

        return null;
    }

    /**
     * 插入
     *
     * @param value
     */
    public void insert(int value) {
        int level = head.forwards[0] == null ? 1 : randomLevel();

        if (level > levelCount) {
            level = ++levelCount;
        }
        Node newNode = new Node(level, value);
        Node update[] = new Node[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }
        Node p = head;
        //从最大层开始找，找到前一节点，通过--i，移动打下一层再开始查找
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            if (level > 1) {
                update[i] = p;
            }
        }
        for (int i = 0; i < level; i++) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;
        }

    }

    /**
     * 随机 level 次，如果是奇数层数 +1，防止伪随机
     *
     * @return
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (r.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    /**
     * 跳表节点
     */
    public class Node {
        private int data = -1;

        private Node forwards[];

        private int maxLevel = 0;

        public Node(int level, int value) {
            this.data = value;
            this.forwards = new Node[level];
        }

        public Node(int level) {
            this.forwards = new Node[level];
        }
    }
}
