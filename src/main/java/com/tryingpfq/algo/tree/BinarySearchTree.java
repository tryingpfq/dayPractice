package com.tryingpfq.algo.tree;

/**
 * @author tryingpfq
 * @date 2020/9/7
 **/
public class BinarySearchTree {
    private Node tree;

    /**
     * 查找
     *
     * @param data
     * @return
     */
    public Node find(int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) {
                p = p.left;
            } else if (data > p.data) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }


    /**
     * 插入
     *
     * @param data
     */
    public void insert(int data) {
        if (tree == null) {
            tree = new Node(data);
            return;
        }
        Node p = tree;
        while (p != null) {
            if (data > p.data) {
                if (p.right == null) {
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    /**
     * 删除
     *
     * @param data
     */
    public void delete(int data) {
        Node p = tree;
        Node pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        //没有找到要删除的节点
        if (p == null) {
            return;
        }
        if (p.right != null && p.left != null) {
            Node minP = p.right;
            Node minPP = p;
            while (p.left != null) {
                minPP = p;
                minP = p.left;
            }
            p.data = minP.data;
            p = minP;
            pp = minPP;
        }
        // 删除节点是叶子节点或者仅有一个子节点
        Node child; // p的子节点
        if (p.left != null)
            child = p.left;
        else if (p.right != null)
            child = p.right;
        else
            child = null;

        if (pp == null)
            tree = child; // 删除的是根节点
        else if (pp.left == p)
            pp.left = child;
        else
            pp.right = child;
    }

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
