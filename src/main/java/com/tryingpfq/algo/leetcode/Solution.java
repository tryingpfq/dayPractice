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
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        ListNode p = head;
        while (p != null) {
            ListNode back = p.next;
            if (p.val != val) {
                tail.next = p;
                tail = p;
            }
            p = back;
        }
        tail.next = null;
        return dummy.next;
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
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        ListNode p = head;
        while (p != null) {
            ListNode back = p.next;
            if (tail.next == null || p.val != tail.val) {
                tail.next = p;
                tail = p;
            }
            p = back.next;
        }
        tail.next = null;
        return dummy.next;
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
                int num = apples[i - 1];
                int badDay = days[i - 1] + i;
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

    static class ANode {
        public int num;

        public int badDay;

        public ANode(int num, int badDay) {
            this.num = num;
            this.badDay = badDay;
        }
    }

    public ListNode reverseList1(ListNode head) {
        ListNode dummy = new ListNode();

        while (head != null) {
            ListNode temp = head.next;
            head.next = dummy.next;
            dummy.next = head;
            head = temp;
        }
        return dummy.next;
    }


    public static String add(String a, String b) {
        int len1 = a.length() - 1;
        int len2 = b.length() - 1;
        int c = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = len1, j = len2; ; i--, j--) {
            if (i < 0 && j < 0) {
                break;
            }
            int v1 = i >= 0 ? a.charAt(i) - '0' : 0;
            int v2 = j >= 0 ? a.charAt(i) - '0' : 0;
            stringBuilder.append((v1 + v2 + c) % 2);
            c = (v1 + v2) / 2;
        }
        if (c > 0) {
            stringBuilder.append('1');
        }
        return String.valueOf(stringBuilder.reverse());
    }


    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        Set<Integer> ids = new HashSet<>(2);
        for (int i = 0; i < nums.length; i++) {
            if (!ids.add(nums[i])) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 剑指offer
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int col = matrix.length, com = matrix[0].length;

        int i = 0, j = com - 1;
        while (i < col && j >= 0) {
            if (matrix[i][j] > target) {
                j--;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指offer 替换空格
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder stringBuilder = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(s.charAt(i));
            }
        }
        return String.valueOf(stringBuilder);
    }

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }
        p = head;
        int[] val = new int[len];
        while (p != null) {
            len--;
            val[len] = p.val;
            p = p.next;
        }

        return val;
    }

    /**
     * 剑指offer
     *
     * @param preorder
     * @param inorder
     * @return
     */

    public Map<Integer, Integer> indexMap = new HashMap<>();

    public TreeNode myBuildTree(int[] preorder, int preorder_left, int preorder_right, int inorder_left) {
        if (preorder_left > preorder_right) {
            return null;
        }
        //root 在中序数组中的位置
        int rIndex_onOrder = indexMap.get(preorder_left);
        int rValue = preorder[preorder_left];
        int size = rIndex_onOrder - inorder_left;
        TreeNode root = new TreeNode(rValue);
        //左子树 修改pre dex
        root.left = myBuildTree(preorder, preorder_left + 1, preorder_left + size, inorder_left);
        root.right = myBuildTree(preorder, preorder_left + size + 1, preorder_right, rIndex_onOrder + 1);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, n - 1, 0);
    }

    /**
     * 113
     *
     * @param root
     * @param targetSum
     * @return
     */

    private List<List<Integer>> ansList = null;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return null;
        }
        ansList = new ArrayList<>();

        List<Integer> path = new ArrayList<>();
        backTree(root, path, 0, targetSum);
        return ansList;
    }

    private void backTree(TreeNode root, List<Integer> path, int sum, int target) {
        if (root == null) {
            return;
        }

        path.add(root.val);

        sum += root.val;

        if (root.left == null && root.right == null) {
            if (sum == target) {
                ansList.add(new ArrayList<>(path));
            }
        } else {
            backTree(root.left, path, sum, target);
            backTree(root.right, path, sum, target);
        }
        path.remove(path.size() - 1);

    }

    public static int fib(int n) {
        int a = 0, b = 1, sum = 0;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    public static int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int a, b;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) {
                stack.push(i);
            } else if (!stack.isEmpty()) {
                int index = stack.pop();
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
            }
        }
        return nums;
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int[] res = new int[matrix.length * matrix[0].length];
        int u = 0, d = matrix.length - 1, l = 0, r = matrix[0].length - 1;
        int idx = 0;
        while (true) {
            for (int i = l; i <= d; i++) {
                res[idx++] = matrix[u][i];
            }
            if (++u > d) {
                break;
            }
            for (int i = u; i <= d; i++) {
                res[idx++] = matrix[i][r];
            }
            if (--r < l) {
                break;
            }

        }
        return res;
    }

    public char firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.merge(s.charAt(i), 1, Integer::sum);
        }

        for (int i = 0; i < s.length(); i++) {
            Integer count = map.get(s.charAt(i));
            if (count != null) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    public String reverseLeftWords(String s, int n) {
        int len = s.length();
        char[] val = new char[len];
        for (int i = 0; i < len; i++) {
            if (i < n) {
                val[len - n + i] = s.charAt(i);
            } else {
                val[i - n] = s.charAt(i);
            }
        }
        return new String(val);
    }

    public String reverseWords(String s) {
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) != ' ') i--;

            //拼接
            res.append(s.substring(i + 1, j + 1));

            //排除空格
            while (i >= 0 && s.charAt(i) == ' ') ;
        }
        return res.toString().trim(); // 转化为字符串并返回
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return check(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public boolean check(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null || a.val != b.val) {
            return false;
        }
        return check(a.right, b.right) && check(a.left, b.left);
    }


    public String minNumber(int[] nums) {
        Queue<String> queue = new PriorityQueue<>((v1, v2) -> {
            long sum1 = Long.parseLong(v1 + v2);
            long sum2 = Long.parseLong(v2 + v1);
            if (sum1 > sum2) {
                return -1;
            } else {
                return 1;
            }
        });

        for (Integer num : nums) {
            queue.offer(String.valueOf(num));
        }
        if (queue.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();


        while (!queue.isEmpty()) {
            stringBuilder.append(queue.poll());
        }
        return stringBuilder.toString();
    }

    public static double myPow(double x, int n) {
        double result = 1;
        if (n == 0) {
            return 1;
        }
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                result *= x;
            }
        } else {
            for (int i = 0; i < -n; i++) {
                result *= x;
            }
            result = 1 / result;
        }
        return result;
    }

    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> result = new ArrayList<>();
        while (!result.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                result.add(temp.val);
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }
        return null;
    }

    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder("");
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) : 0;
            int n2 = j >= 0 ? num2.charAt(j) : 0;
            int sum = n1 + n2 + carry;

            carry = sum / 10;
            res.append(carry);
            i--;
            j--;
        }
        if (carry == 1) res.append(1);
        return res.reverse().toString();
    }


    public static void main(String[] args) {
        double v = myPow(2, -2);
        System.err.println(v);
    }


    public String intToRoman(int num) {
        StringBuffer stringBuffer = new StringBuffer();
        int[] val = new int[]{1000, 500, 100, 50, 10, 5, 1};
        for (int i = 0; i < val.length; i++) {

        }
        return "";
    }
}
