package com.tryingpfq.algo.leetcode;

import org.apache.commons.lang.ArrayUtils;

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

        ListNode() {

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

    public static void main1(String[] args) {
        ListNode p = new ListNode(1);
        int i = 2;
        ListNode head = p;
        p.next = new ListNode(2);
        p.next.next = new ListNode(4);

        ListNode q = new ListNode(1);
        q.next = new ListNode(3);
        q.next.next = new ListNode(4);

        print(q);
        mergeTwoLists(p, q);
        System.out.println("after");
        print(q);
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
    private void preOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        preOrder(root.left, list);
        preOrder(root.right, list);
    }

    /**
     * 有效的括号 20
     *
     * @param s
     * @return
     */


    public boolean isValid(String s) {
        HashMap<Character, Character> mappings = new HashMap<>();
        mappings.put(')', '(');
        mappings.put(']', '[');
        mappings.put('}', '{');


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

    /**
     * 二叉树最大深度
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /**
     * 字符串相加
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer sb = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            sb.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        return sb.reverse().toString();

    }


    /**
     * 337 打家劫舍
     *
     * @param root
     * @return
     */
    public int rob3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int total = root.val;
        if (root.left != null) {
            total += rob3(root.left.left) + rob3(root.left.right);
        }

        if (root.right != null) {
            total += rob3(root.right.left) + rob3(root.right.right);
        }
        return Math.max(total, rob3(root.left) + rob3(root.right));

    }

    //ro3改进一
    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> memory = new HashMap<>();
        return rot3_1(root, memory);
    }

    public int rot3_1(TreeNode root, HashMap<TreeNode, Integer> memory) {
        if (root == null) {
            return 0;
        }
        if (memory.containsKey(root)) {
            return memory.get(root);
        }
        int total = root.val;
        if (root.left != null) {
            total += rot3_1(root.left.left, memory) + rot3_1(root.left.right, memory);
        }
        if (root.right != null) {
            total += rot3_1(root.right.left, memory) + rot3_1(root.right.right, memory);
        }
        int result = Math.max(total, rot3_1(root.left, memory) + rot3_1(root.right, memory));
        memory.put(root, result);
        return result;
    }


    /**
     * 滑动窗口
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k == 0) {
            return null;
        }
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int j = 0, i = 1 - k; j < nums.length; i++, j++) {
            if (i > 0 && deque.peekFirst() == nums[i - 1])
                deque.removeFirst(); // 删除 deque 中对应的 nums[i-1]
            while (!deque.isEmpty() && deque.peekLast() < nums[j])
                deque.removeLast(); // 保持 deque 递减
            deque.addLast(nums[j]);
            if (i >= 0)
                res[i] = deque.peekFirst();  // 记录窗口最大值
        }
        return res;
    }

    /**
     * 687 最长同值路劲
     *
     * @param root
     * @return
     */
    int ans = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return arrowLength(root);
    }

    public int arrowLength(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

    /**
     * 100 相同的树
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val == q.val) {
            return false;
        }
        return isSameTree(p.right, q.right) && isSameTree(p.left, q.left);
    }

    /**
     * 696 计数二进制子串
     *
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        List<Integer> counts = new ArrayList<>();
        int ptr = 0, n = s.length();
        while (ptr < n) {
            char c = s.charAt(ptr);
            int count = 1;
            while (ptr < n && s.charAt(ptr) == c) {
                ptr++;
                count++;
            }
            counts.add(count);
        }
        int ans = 0;
        for (int i = 1; i < counts.size(); i++) {
            ans += Math.min(counts.get(i), counts.get(i - 1));
        }
        return ans;
    }


    /**
     * 349 两个数组的交集
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        Set<Integer> set1 = new HashSet<>(Arrays.asList(ArrayUtils.toObject(nums1)));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(ArrayUtils.toObject(nums1)));
        int[] result = new int[set1.size()];
        int index = 0;
        for (Integer val : set1) {
            if (set2.contains(val)) {
                result[index] = val;
                index++;
            }
        }
        return result;
    }

    /**
     * 被围绕的区域 图 130
     *
     * @param board
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                boolean isEdge = i == 0 || j == 0 || i == m - 1 || j == n - 1;
                if (isEdge && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length ||
                board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        board[i][j] = '#';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }

    /**
     * 重复的子字符串
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int i = 1; i * 2 <= n; ++i) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * 机器人能否回到原点 657
     *
     * @param moves
     * @return
     */
    private static final char CHAR_U = 'U';
    private static final char CHAR_D = 'D';
    private static final char CHAR_R = 'R';
    private static final char CHAR_L = 'L';

    public static boolean judgeCircle(String moves) {
        if (moves == null || moves.length() == 0) {
            return true;
        }
        int L = 0, R = 0, U = 0, D = 0;
        for (int i = 0; i < moves.length(); i++) {
            char ch = moves.charAt(i);
            switch (ch) {
                case CHAR_U:
                    U++;
                    break;
                case CHAR_D:
                    U++;
                    break;
                case CHAR_L:
                    L++;
                    break;
                case CHAR_R:
                    R++;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + ch);
            }
        }
        if (L != R || U != D) {
            return false;
        }
        return true;
    }

    /**
     * 33 搜索旋转有序数组
     *
     * @param nums   数组
     * @param target 目标
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n <= 0) {
            return -1;
        }
        if (n == 1) return nums[0] == target ? 0 : -1;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            if (nums[0] <= nums[mid]) { // 这个区间不存在旋转
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 841 钥匙和房间
     *
     * @param rooms
     * @return
     */
    boolean[] vis;
    int num;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        num = 0;
        vis = new boolean[n];
        dfs(rooms, 0);
        return num == n;
    }

    public void dfs(List<List<Integer>> rooms, int x) {
        vis[x] = true;
        num++;
        for (int i : rooms.get(x)) {
            if (!vis[i]) {
                dfs(rooms, i);
            }
        }
    }

    /**
     * 347. 前 K 个高频元素
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

    /**
     * 215. 数组中的第K个最大元素
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return -1;
    }


    /**
     * 2 两数之和
     *
     * @param l1
     * @param l2
     * @return
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int last = l1.val + l2.val;
        int inc = last / 10;
        ListNode p = new ListNode(last % 10);
        ListNode result = p;
        while (l1.next != null || l2.next != null) {
            int a = 0;
            if (l1.next != null) {
                l1 = l1.next;
                a = l1.val;
            }
            int b = 0;
            if (l2.next != null) {
                l2 = l2.next;
                b = l2.val;
            }
            int sum = inc + a + b;
            inc = sum / 10;
            p.next = new ListNode(sum % 10);
            p = p.next;
        }
        if (inc > 0) {
            p.next = new ListNode(inc);
        }
        return result;
    }

    /**
     * 3 无重复字符串的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && occ.add(s.charAt(rk + 1))) {
                // 不断地移动右指针CountDownLatch
                // occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        int len = arr.length;
        if (len < k) {
            return null;
        }
        quickSort(arr, 0, arr.length - 1, k);
        int[] vic = new int[k];
        for (int i = 0; i < k; i++) {
            vic[i] = arr[i];
        }
        return vic;
    }

    private static void quickSort(int[] arr, int p, int r, int k) {
        if (p >= r) {
            return;
        }
        int pos = pos(arr, p, r);
//        if (pos == k) {
//            return;
//        }
        quickSort(arr, p, pos - 1, k);
        quickSort(arr, pos + 1, r, k);
    }

    private static int pos(int[] arr, int p, int r) {
        int privot = arr[r];
        int i = p;
        for (int j = p; j < r; j++) {
            if (arr[j] > privot) {
                if (i == j) {
                    i++;
                } else {
                    int temp = arr[i];
                    arr[i++] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        int temp = arr[r];
        arr[r] = arr[i];
        arr[i] = temp;
        return i;
    }

    private static void topK(int[] data, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < data.length; i++) {
            if (queue.size() < k) {
                queue.offer(data[i]);
            } else {
                int value = queue.peek();
                if (data[i] > value) {
                    queue.poll();
                    queue.offer(data[i]);
                }
            }
        }
        System.err.println(queue);
    }

    /**
     * 82. 删除排序链表中的重复元素 II
     */
    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            while (head != null && head.next != null && head.next.val == head.val) {
                head = head.next;
            }
            return deleteDuplicates2(head.next);
        } else {
            head.next = deleteDuplicates2(head.next);
            return head;
        }
    }

    /**
     * 剑指offer 68 二叉树最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }
        return root;
    }

    /**
     * 53 最大子序和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int max = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            max = Math.max(pre, max);
        }
        return max;
    }


    private int x;
    private TreeNode xParent;
    private int xDepth;
    private boolean xFound;

    private int y;
    private TreeNode yParent;
    private int yDepth;
    private boolean yFound;

    public boolean isCousins(TreeNode root, int x, int y) {
        this.x = x;
        this.y = y;
        dfs(root, 0, null);
        return xDepth == yDepth && xParent != yParent;
    }

    public void dfs(TreeNode treeNode, int depth, TreeNode parent) {
        if (treeNode == null) {
            return;
        }
        if (treeNode.val == x) {
            xParent = parent;
            this.xDepth = depth;
            this.xFound = true;
        } else if (treeNode.val == y) {
            this.yParent = parent;
            this.yDepth = depth;
            this.yFound = true;
        }

        if (xFound && yFound) {
            return;
        }

        dfs(treeNode.left, depth + 1, treeNode);

        if (xFound && yFound) {
            return;
        }

        dfs(treeNode.right, depth + 1, treeNode);

        if (xFound && yFound) {
            return;
        }

    }


    public static int[] getLeastNumbers1(int[] arr, int k) {
        if (k <= 0 || arr == null || arr.length < k) {
            return new int[0];
        }

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((num1, num2) -> num2 - num1);

        for (int i = 0; i < arr.length; i++) {
            queue.offer(arr[i]);

            if (queue.size() > k) {
                queue.poll();
            }
        }

        int[] ans = new int[k];
        int i = 0;
        while (!queue.isEmpty()) {
            ans[i] = queue.poll();
            i++;
        }
        return ans;
    }


    public List<String> topKFrequent(String[] words, int k) {
        int N = words == null ? 0 : words.length;
        if (k <= 0) {
            return new ArrayList<>();
        }

        List<String> ans = new ArrayList<>(k);

        Counter counter = new Counter();
        for (int i = 0; i < N; i++) {
            counter.add(words[i], 1);
        }

        Queue<NVal> queue = new PriorityQueue<>((v1, v2) -> {
            if (v1.cnt != v2.cnt) {
                return v1.cnt - v2.cnt;
            }
            return v2.str.compareTo(v2.str);
        });

        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            queue.offer(new NVal(entry.getKey(), entry.getValue()));
            if (queue.size() > k) {
                queue.poll();
            }
        }

        while (!queue.isEmpty()) {
            ans.add(queue.poll().str);
        }
        Collections.reverse(ans);

        return ans;
    }

    class NVal {
        public String str;
        public int cnt = 0;

        public NVal(String str, int val) {
            this.str = str;
            this.cnt = val;
        }
    }

    /**
     * 合并k个升序链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        final int N = lists == null ? 0 : lists.length;
        Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.val));

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.offer(lists[i]);
            }
        }

        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            tail.next = cur;
            tail = cur;
            if (cur.next != null) {
                queue.offer(cur.next);
            }
        }

        return dummy.next;
    }

    class Counter extends HashMap<String, Integer> {
        @Override
        public Integer get(Object key) {
            return containsKey(key) ? super.get(key) : 0;
        }


        public void add(String str, int v) {
            put(str, get(str) + v);
            if (get(str) <= 0) {
                remove(str);
            }
        }
    }

    /**
     * 1642
     *
     * @param heights
     * @param bricks
     * @param ladders
     * @return
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int preHigh = heights[0];
        Queue<Integer> queue = new PriorityQueue<>((v1, v2) -> v2 - v1);

        int sum = 0;
        int lastPos = 0;

        for (int i = 1; i < heights.length; i++) {
            int curHigh = heights[i];
            if (preHigh >= curHigh) {
                lastPos = i;
            } else {
                int delta = curHigh - preHigh;
                queue.offer(delta);

                sum += delta;
                while (sum > bricks && ladders > 0) {
                    Integer poll = queue.poll();
                    sum -= poll;
                    ladders--;
                }
                if (sum <= bricks) {
                    lastPos = i;

                } else {
                    break;
                }
            }
            preHigh = curHigh;
        }
        return lastPos;
    }

    /**
     * 1705
     *
     * @param apples
     * @param days
     * @return
     */
    public static int eatenApples(int[] apples, int[] days) {
        int N = apples == null ? 0 : apples.length;

        Queue<ANode> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.badDay));

        int ans = 0;
        int i = 1;
        while (i <= N || !queue.isEmpty()) {
            if (i <= N) {
                int num = apples[i-1];
                int badDay = days[i-1] + i;
                if (num > 0) {
                    queue.offer(new ANode(num, badDay));
                }
            }
            while (!queue.isEmpty() && queue.peek().badDay <= i) {
                queue.poll();
            }

            if (!queue.isEmpty()) {
                //选出今天要吃的
                ANode node = queue.poll();
                ans++;
                node.num -= 1;
                if (node.num > 0) {
                    queue.offer(node);
                }
            }
            i++;
        }
        return ans;
    }

    static class ANode{
        public int num;

        public int badDay;

        public ANode(int num, int badDay) {
            this.num = num;
            this.badDay = badDay;
        }
    }

    public static void main(String[] args) {
        int[] apples =new int[] {1,2,3,5,2}, days = new int[]{3,2,1,4,2};
        int i = eatenApples(apples, days);
        System.err.println(i);
    }


    public String intToRoman(int num) {
        StringBuffer stringBuffer = new StringBuffer();
        int[] val = new int[]{1000, 500, 100, 50, 10, 5, 1};
        for (int i = 0; i < val.length; i++) {

        }
        return "";
    }
}
