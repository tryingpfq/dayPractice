package com.tryingpfq.algo.leetcode;

import java.util.*;

/**
 * @author tryingpfq
 * @date 2020/7/20
 **/
public class Solution {


    /**
     * 两数之和 167
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] index = new int[]{-1, -1};
        if (numbers == null || numbers.length < 2) {
            return index;
        }
//        for (int i = 0; i < numbers.length -1 ; i++) {
//            int first = numbers[i];
//            for (int j = i + 1; j < numbers.length; j++) {
//                int second = numbers[j];
//                if (first + second == target) {
//                    index[0] = i+1;
//                    index[1] = j+1;
//                    break;
//                }
//            }
//        }

//        for (int i = 0; i < numbers.length; i++) {
//            int low = i + 1, high = numbers.length - 1;
//            while (low <= high) {
//                int mid = (high - low) / 2 + low;
//                if (numbers[mid] == target - numbers[i]) {
//                    index[0] = i + 1;
//                    index[1] = mid + 1;
//                    break;
//                } else if (numbers[mid] > target - numbers[i]) {
//                    high = mid - 1;
//                } else {
//                    low = mid + 1;
//                }
//            }
//        }

        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }

        return index;
    }

    /**
     * 二叉搜素树 95
     */
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 最小路劲 动态规划 64
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int row = grid.length, columns = grid[0].length;
        int[][] dp = new int[row][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][columns - 1];
    }


    /**
     * 矩阵中最长递增路径
     * 329
     */

    public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int rows, columns;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        rows = matrix.length;
        columns = matrix[0].length;
        int[][] memo = new int[rows][columns];
        int ans = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }
        return ans;
    }


    public int dfs(int[][] matrix, int row, int column, int[][] memo) {
        if (memo[row][column] != 0) {
            return memo[row][column];
        }
        ++memo[row][column];
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newColumn = column + dir[1];
            if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[row][column]) {
                memo[row][column] = Math.max(memo[row][column], dfs(matrix, newRow, newColumn, memo) + 1);
            }
        }
        return memo[row][column];
    }

    /**
     * 单链表回文判断 234
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }
    }

    /**
     * 回文链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode fast = head, slow = head;

        //找到中间节点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode pre = null;
        while (slow != null) {
            ListNode p = slow.next;
            slow.next = pre;
            pre = slow;
            slow = p;
        }

        while (head != null && pre != null) {
            if (head.val != pre.val) {
                return false;
            }
            head = head.next;
            pre = pre.next;
        }
        return true;
    }

    /**
     * 删除链表元素 203
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode pre = head;
        ListNode p = head.next;
        while (p != null) {
            if (p.val == val) {
                pre.next = p.next;
                p = pre.next;
            } else {
                pre = p;
                p = p.next;
            }
        }
        return head;
    }

    /**
     * 删除有序链表重复元素 83
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode p = head.next;
        while (p != null) {
            if (pre.val == p.val) {
                pre.next = p.next;
            } else {
                pre = p;
            }
            p = p.next;
        }
        return head;
    }

    /**
     * 判断子序列 392
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence(String s, String t) {
        //双指针
        if (s == null || t == null || s.length() > t.length()) {
            return false;
        }
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    public static void main(String[] args) {
        ListNode p = new ListNode(1);
        int i = 2;
        ListNode head = p;
        p.next = new ListNode(2);
        p.next.next = new ListNode(4);

        ListNode q = new ListNode(1);
        q.next = new ListNode(3);
        q.next.next = new ListNode(4);

        mergeTwoLists(p, q);
    }

    /**
     * 反转链表206
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    public static void print(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        //剩余的长度处理
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }


    /**
     * 二叉树 展开为链表 114
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            TreeNode prev = list.get(i - 1), curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }

    }

    /**
     * 前序递归遍历
     */
    private void preOrder(TreeNode root,  List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        preOrder(root.left, list);
        preOrder(root.right,list);
    }

    /**
     * 有效的括号 20
     *
     * @param s
     * @return
     */



    public boolean isValid(String s) {
        HashMap<Character, Character> mappings = new HashMap<>();
        mappings.put(')','(');
        mappings.put(']','[');
        mappings.put('}','{');


        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mappings.containsKey(c)) {
                char top = stack.empty() ? '#' : stack.pop();

                if (top != mappings.get(c)) {
                    return false;
                }
            }
            stack.push(c);
            if (stack.size() > s.length() / 2 + 1) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
