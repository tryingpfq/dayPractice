package com.tryingpfq.algo.leetcode;

import java.util.LinkedList;
import java.util.List;

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
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid){
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }
        int row = grid.length,columns = grid[0].length;
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

}
