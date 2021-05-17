package com.tryingpfq.algo.lagou;

import java.util.*;

/**
 * 拉钩教育
 *
 * @author tryingpfq
 * @date 2021/5/7
 **/
public class Demo {

    private static Map<Character, Character> characterMap = new HashMap<>();

    static {
        characterMap.put('(', ')');
    }

    public static void main(String[] args) {
        String str = "(he))";
        Stack<Character> stack = new Stack<>();
        for (char character : str.toCharArray()) {
            if ('(' == character) {
                stack.push(character);
            } else if (')' == character) {
                if (stack.isEmpty()) {
                    System.err.println("false");
                }
                char c = stack.pop();
                if (c != '(') {
                    System.err.println("false");
                }
            }
        }
        if (!stack.isEmpty()) {
            System.err.println("false");
        }
    }


    //==================栈=================//

    /**
     * 大鱼吃小鱼
     */
    public static int fish(int[] fishSize, int[] fishDir) {
        if (fishSize.length <= 1) {
            return fishDir.length;
        }
        final int left = 0, right = 1;
        Stack<Integer> pos = new Stack<>();

        for (int i = 0; i < fishSize.length; i++) {
            int curSize = fishSize[i];
            int curDir = fishDir[i];
            boolean hasEat = false;
            while (!pos.isEmpty() && fishDir[pos.peek()] == right && curDir == left) {
                if (fishSize[pos.peek()] > curSize) {
                    hasEat = true;
                    break;
                }
                pos.pop();
            }
            if (!hasEat) {
                pos.push(i);
            }
        }
        return pos.size();
    }

    public static int[] findRightSmall(int[] A) {
        int[] ans = new int[A.length];
        Stack<Integer> t = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            int curV = A[i];
            while (!t.isEmpty() && A[t.peek()] > curV) {
                ans[t.peek()] = i;
                t.pop();
            }
            t.push(i);
        }
        while (!t.isEmpty()) {
            ans[t.peek()] = -1;
            t.pop();
        }
        return ans;
    }


    public static int[] findRightBig(int[] A) {
        int[] ans = new int[A.length];
        Stack<Integer> t = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            int curV = A[i];
            while (t.isEmpty() && A[t.peek()] < curV) {
                ans[t.peek()] = i;
                t.pop();
            }
            t.push(i);
        }
        while (!t.isEmpty()) {
            ans[t.peek()] = -1;
            t.pop();
        }
        return ans;
    }


    //===============队列=============//

    /**
     * 二叉树层次遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> result = new LinkedList<>();
        while (!queue.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                temp.add(cur.getVal());
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            result.add(temp);
        }
        return result;
    }

    public List<Double> averageOflevels(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root != null) {
            q.offer(root);
        }
        List<Double> ans = new LinkedList<>();
        while (q.isEmpty()) {
            int size = q.size();
            double temp = 0;
            for (int i = 0; i < size; i++) {
                TreeNode poll = q.poll();
                temp += poll.val;
                if (poll.left != null) {
                    q.offer(poll.left);
                }
                if (poll.right != null) {
                    q.offer(poll.right);
                }
            }
            ans.add(temp / size);
        }
        return ans;
    }

    /**
     * 二叉树最大宽度
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        Queue<Pair> a = new LinkedList<>();
        if (root != null) {
            a.offer(new Pair(root, 0));
        }
        int ans = 0;
        while (!a.isEmpty()) {
            int size = a.size();
            int start = -1,end = -1;
            for (int i = 0; i < size; i++) {
                Pair cur = a.poll();
                if (-1 == start) {
                    start = cur.id;
                }
                end = cur.id;
                if (cur.node.left != null) {
                    a.offer(new Pair(cur.node.left, cur.id << 1));
                }
                if (cur.node.right != null) {
                    a.offer(new Pair(cur.node.right, cur.id >> 1));
                }
            }
            ans = Math.max(ans, end - start + 1);
        }
        return ans;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        List<List<Integer>> result = new LinkedList<>();
        boolean reverse = false;
        while (!queue.isEmpty()) {
            int size = queue.size();

            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                temp.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }

            if (reverse) {
                Collections.reverse(temp);
            }
            result.add(temp);
            reverse = !reverse;
        }
        return result;
    }


    /**
     * 滑动窗口最大值
     * @param data
     * @param k 长度
     * @return
     */
    public int[] maxVal(int[] data, int k) {
        if (data.length < k) {
            return null;
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[data.length - k + 1];
        int max = 0;
        for (int i = 0; i < data.length; i++) {
            queue.offer(data[i]);
            if (queue.size() < k) {
                if (max < data[i]) {
                    max = data[i];
                }
            }else{
                Integer poll = queue.poll();
            }

        }
        return null;
    }

    public static class Pair {
        private TreeNode node;
        private int id;

        public Pair(TreeNode node, int id) {
            this.node = node;
            this.id = id;
        }
    }


    public static class TreeNode {
        private int val;

        private TreeNode left;

        private TreeNode right;

        public int getVal() {
            return val;
        }
    }


}
