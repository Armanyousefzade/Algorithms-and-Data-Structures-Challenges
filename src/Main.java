import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();

        for (int number : nums) {
            set.add(number);
        }

        int length;
        int maxLength = 0;

        for (int number : nums) {
            length = 1;

            while (set.contains(++number)) {
                length++;
            }

            if (length > maxLength) {
                maxLength = length;
            }
        }

        return maxLength;
    }

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();

        String filtered = "";

        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) >= '0' && s.charAt(i) <= '9') || (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')) {
                filtered += s.charAt(i);
            }
        }

        for (int j = 0; j < filtered.length(); j++) {
            if (filtered.charAt(j) != filtered.charAt(filtered.length() - 1 - j)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
     * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     *
     * @param nums the integer array
     * @return a list of triplets that satisfy the given conditions
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> resultSet = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                } else if (nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                } else {
                    List<Integer> triple = new ArrayList<>();
                    triple.add(nums[i]);
                    triple.add(nums[j]);
                    triple.add(nums[k]);
                    if (resultSet.add(triple)) {
                        result.add(triple);
                    }
                    j++;
                    k--;
                }
            }
        }

        return result;
    }

    /**
     * Calculates the maximum area formed by vertical lines and an array of heights.
     * The area is determined by the width between two vertical lines (indices) and the minimum height among them.
     * This method implements the two-pointer approach to optimize the computation.
     *
     * @param height An array of integers representing the heights of the vertical lines.
     * @return The maximum area formed by the vertical lines.
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int max = -1;

        while (left < right) {
            int storeAmount = (right - left) * Math.min(height[left], height[right]);
            if (storeAmount > max) {
                max = storeAmount;
            }

            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }

    /**
     * Calculates the maximum profit that can be obtained from a given array of prices.
     *
     * @param prices an array of prices
     * @return the maximum profit that can be obtained
     */
    public int maxProfit(int[] prices) {
        int left = 0;
        int right = left + 1;

        int maxProfit = 0;

        while (right < prices.length) {
            if (prices[left] < prices[right]) {
                maxProfit = Math.max(maxProfit, prices[right] - prices[left]);
                right++;
            } else {
                left = right;
                right++;
            }
        }

        return maxProfit;
    }

    /**
     * Returns the length of the longest substring without repeating characters in the given string.
     *
     * @param s the input string
     * @return the length of the longest substring without repeating characters
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastOccurance = new HashMap<>();

        int left = 0;
        int right = 0;

        int maxLength = 0;

        while (right < s.length()) {

            if (lastOccurance.containsKey(s.charAt(right))) {
                left = lastOccurance.get(s.charAt(right)) + 1;
                right = left;
                lastOccurance.clear();
            } else {
                lastOccurance.put(s.charAt(right), right);
                right++;
            }

            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }

    /**
     * Calculates the maximum length of a substring with the same character in the given string 's' after replacing at most 'k' characters.
     *
     * @param s The input string.
     * @param k The maximum number of characters that can be replaced.
     * @return The maximum length of a substring after replacement.
     */
    public int characterReplacement(String s, int k) {
        int left = 0;
        int right = 0;
        int maxLength = 0;

        int maxCount = 0;
        int[] counts = new int[26];

        while (right < s.length()) {
            maxCount = Math.max(maxCount, ++counts[s.charAt(right) - 'A']);

            if (maxCount + k <= right - left) {
                counts[s.charAt(left) - 'A']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }

    /**
     * Checks if a string containing parentheses, brackets, and braces is valid.
     *
     * @param s the string to be checked
     * @return true if the string is valid, false otherwise
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        }

        return false;
    }

    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    /**
     * Finds the minimum value in an array of integers.
     *
     * @param nums  the array of integers
     * @return the minimum value in the array
     */
    public int findMin(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int median = (left + right) / 2;

        if (nums[median] > nums[right]) {
            return findMin(nums, median + 1, right);
        } else {
            return findMin(nums, left, median);
        }
    }

    /**
     * Searches for the target element in the given rotated sorted array.
     *
     * @param nums   The rotated sorted array.
     * @param target The target element to search for.
     * @return The index of the target element if found, or -1 if not found.
     */
    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    /**
     * Recursive helper method to perform the binary search for the target element in the rotated sorted array.
     *
     * @param nums   The rotated sorted array.
     * @param target The target element to search for.
     * @param left   The left index of the current search range.
     * @param right  The right index of the current search range.
     * @return The index of the target element if found, or -1 if not found.
     */
    public int search(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (nums[left] <= nums[mid]) {
            if (target >= nums[left] && target < nums[mid]) {
                return search(nums, target, left, mid - 1);
            } else {
                return search(nums, target, mid + 1, right);
            }
        } else {
            if (target > nums[mid] && target <= nums[right]) {
                return search(nums, target, mid + 1, right);
            } else {
                return search(nums, target, left, mid - 1);
            }
        }
    }

    /**
     * Reverses a linked list.
     *
     * @param head The head node of the linked list.
     * @return The new head node of the reversed linked list.
     */
    public ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode next = null;
        ListNode prev = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        return prev;
    }

    /**
     * Merges two sorted linked lists into a single sorted linked list.
     *
     * @param list1 The head of the first sorted linked list.
     * @param list2 The head of the second sorted linked list.
     * @return The head of the merged sorted linked list.
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode list3 = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                list3.next = list1;
                list1 = list1.next;
            } else {
                list3.next = list2;
                list2 = list2.next;
            }
            list3 = list3.next;
        }

        if (list1 != null) {
            list3.next = list1;
        }

        if (list2 != null) {
            list3.next = list2;
        }

        return dummy.next;
    }

    /**
     * Inverts a binary tree by swapping the left and right children of each node recursively.
     *
     * @param root The root of the binary tree to be inverted.
     * @return The root of the inverted binary tree.
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.right);
        invertTree(root.left);
        return root;
    }

    /**
     * Calculates the maximum depth of a binary tree.
     *
     * @param root The root of the binary tree.
     * @return The maximum depth of the binary tree.
     */
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    private int maxDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }

        return Math.max(maxDepth(root.left, depth + 1), maxDepth(root.right, depth + 1));
    }

    /**
     * Finds all unique combinations in the given candidates that sum up to the target value.
     *
     * @param candidates The array of candidates to choose from.
     * @param target     The target value to be achieved by the combinations.
     * @return A list of all unique combinations that sum up to the target.
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList();
        combinationSum(candidates, target, cur, ans, 0);
        return ans;
    }

    /**
     * Recursive helper method to find combinations that sum up to the target.
     *
     * @param candidates The array of candidates to choose from.
     * @param target     The remaining target value to be achieved by the combinations.
     * @param cur        The current combination being built.
     * @param ans        The list of all unique combinations found.
     * @param index      The index of the candidate to consider in the current recursive call.
     */
    public void combinationSum(int[] candidates, int target, List<Integer> cur, List<List<Integer>> ans, int index) {
        if (target == 0) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        if (target < 0 || index >= candidates.length) {
            return;
        }

        cur.add(candidates[index]);
        combinationSum(candidates, target - candidates[index], cur, ans, index);

        cur.remove(cur.size() - 1);
        combinationSum(candidates, target, cur, ans, index + 1);
    }

    /**
     * Counts the number of islands in a 2D grid.
     *
     * @param grid The 2D grid represented as a char array.
     * @return The number of islands in the grid.
     */
    public int numIslands(char[][] grid) {
        int numberOfIslands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    numberOfIslands++;
                    removeOnes(grid, i, j);
                }
            }
        }

        return numberOfIslands;
    }

    /**
     * Recursive helper method to remove connected islands represented by '1' in the grid.
     *
     * @param grid The 2D grid represented as a char array.
     * @param i    The row index of the current element.
     * @param j    The column index of the current element.
     */
    public void removeOnes(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        if (i != 0 && grid[i - 1][j] == '1') {
            removeOnes(grid, i - 1, j);
        }
        if (j != 0 && grid[i][j - 1] == '1') {
            removeOnes(grid, i, j - 1);
        }
        if (i != grid.length - 1 && grid[i + 1][j] == '1') {
            removeOnes(grid, i + 1, j);
        }
        if (j != grid[0].length - 1 && grid[i][j + 1] == '1') {
            removeOnes(grid, i, j + 1);
        }
    }

    int[] memory = new int[46];

    /**
     * Calculates the number of distinct ways to climb to the top of the stairs.
     *
     * @param n The number of stairs to climb.
     * @return The number of distinct ways to climb to the top.
     */
    public int climbStairs(int n) {
        if (memory[n] != 0) {
            return memory[n];
        }

        if (n <= 2) {
            memory[n] = n;
            return memory[n];
        }

        memory[n] = climbStairs(n - 1) + climbStairs(n - 2);
        return memory[n];
    }

    int[] memoryRob = new int[101];

    /**
     * Calculates the maximum amount of money that can be robbed from the given houses.
     *
     * @param nums An array representing the amount of money in each house.
     * @return The maximum amount of money that can be robbed.
     */
    public int rob(int[] nums) {
        Arrays.fill(memoryRob, -1);
        return rob(nums, nums.length - 1);
    }

    /**
     * Recursive helper method to calculate the maximum amount of money that can be robbed from the given houses.
     *
     * @param nums An array representing the amount of money in each house.
     * @param n    The index of the current house.
     * @return The maximum amount of money that can be robbed.
     */
    private int rob(int[] nums, int n) {
        if (n < 0) {
            return 0;
        }

        if (memoryRob[n] != -1) {
            return memoryRob[n];
        }

        if (n == 0) {
            memoryRob[n] = nums[n];
            return memoryRob[n];
        }

        memoryRob[n] = Math.max(rob(nums, n - 2) + nums[n], rob(nums, n - 1));
        return memoryRob[n];
    }

    /**
     * Finds the missing number in an array of integers.
     *
     * @param nums An array of integers containing n distinct numbers taken from 0, 1, 2, ..., n, where n is the length of the array.
     * @return The missing number in the array.
     */
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i <= nums.length; i++) {
            set.add(i);
        }

        for (int num : nums) {
            set.remove(num);
        }

        List<Integer> result = new ArrayList<>(set);
        return result.get(0);
    }

    int[][] storage;

    /**
     * Calculates the number of unique paths from the top-left corner to the bottom-right corner
     * in a grid of size m x n.
     *
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @return The number of unique paths from the top-left corner to the bottom-right corner.
     */
    public int uniquePaths(int m, int n) {
        storage = new int[m][n];

        return uniquePaths(0, 0, m, n);
    }

    /**
     * Helper method to calculate the number of unique paths from a given position (i, j)
     * to the bottom-right corner in a grid of size m x n.
     *
     * @param i The current row index.
     * @param j The current column index.
     * @param m The number of rows in the grid.
     * @param n The number of columns in the grid.
     * @return The number of unique paths from the current position to the bottom-right corner.
     */
    private int uniquePaths(int i, int j, int m, int n) {
        if (i >= m || j >= n) {
            return 0;
        }

        if (storage[i][j] != 0) {
            return storage[i][j];
        }

        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        storage[i][j] = uniquePaths(i, j + 1, m, n) + uniquePaths(i + 1, j, m, n);
        return storage[i][j];
    }

    /**
     * Finds the maximum sum of a subarray in the given array.
     *
     * @param nums The input array of integers.
     * @return The maximum sum of a subarray.
     */
    public int maxSubArray(int[] nums) {
        int right = 0;

        int max = Integer.MIN_VALUE;
        int sum = 0;

        while (right < nums.length) {
            sum += nums[right];
            max = Math.max(max, sum);
            right++;

            if (sum < 0) {
                sum = 0;
            }
        }

        return max;
    }

    /**
     * Inserts a new interval into the given array of intervals and merges overlapping intervals if necessary.
     *
     * @param intervals The array of intervals, where each interval is represented by an array of two integers [start, end].
     * @param newInterval The new interval to be inserted, represented by an array of two integers [start, end].
     * @return The resulting array of intervals after insertion and merging.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        for (int[] interval : intervals) {
            if (newInterval == null || newInterval[0] > interval[1]) {
                result.add(interval);
            } else if (newInterval[1] < interval[0]) {
                result.add(newInterval);
                result.add(interval);
                newInterval = null;
            } else {
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }

        if (newInterval != null) {
            result.add(newInterval);
        }

        return result.toArray(new int[result.size()][2]);
    }

    /**
     * Finds the indices of two numbers in the given array that add up to the target value.
     *
     * @param numbers The input array of integers.
     * @param target The target value to find the sum of two numbers.
     * @return An array of two indices representing the positions of the two numbers that add up to the target value.
     *         If no such pair is found, returns null.
     */
    public int[] twoSum(int[] numbers, int target) {
        int leftIndex = 0;
        int rightIndex = 0;

        int leftNumber = 0;
        int rightNumber = 0;

        while (leftIndex < numbers.length) {
            leftNumber = numbers[leftIndex];

            rightIndex = leftIndex + 1;
            while (rightIndex < numbers.length) {
                rightNumber = numbers[rightIndex];

                if (rightNumber > target - leftNumber) {
                    break;
                }

                if (rightNumber == target - leftNumber) {
                    int[] result = new int[2];
                    result[0] = leftIndex + 1;
                    result[1] = rightIndex + 1;

                    return result;
                }

                rightIndex++;
            }
            leftIndex++;
        }

        return null;
    }

    /**
     * Checks if a string s1 is a substring anagram of another string s2.
     *
     * @param s1 The first input string.
     * @param s2 The second input string.
     * @return {@code true} if s1 is a substring anagram of s2, {@code false} otherwise.
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] a = new int[26];
        int[] b = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            a[s1.charAt(i) - 'a']++;
            b[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(a, b)) {
            return true;
        }

        int windowStart = 1;
        int windowEnd = s1.length();

        while (windowEnd < s2.length()) {
            b[s2.charAt(windowStart - 1) - 'a']--;
            b[s2.charAt(windowEnd) - 'a']++;

            if (Arrays.equals(a, b)) {
                return true;
            }

            windowStart++;
            windowEnd++;
        }

        return false;
    }

    /**
     * Generates all subsets of the given array of integers.
     *
     * @param nums the array of integers
     * @return a list of lists containing all subsets
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        subsetsRecursive(nums, 0, result, new ArrayList<Integer>());

        return result;
    }

    /**
     * Recursively generates subsets of the given array of integers.
     *
     * @param nums the array of integers
     * @param index the current index in the array
     * @param result the list of subsets generated so far
     * @param list the current subset being constructed
     */
    private void subsetsRecursive(int[] nums, int index, List<List<Integer>> result, List<Integer> list) {
        if (index == nums.length) {
            result.add(list);
            return;
        }

        List<Integer> list2 = new ArrayList<>(list);
        list2.add(nums[index++]);

        subsetsRecursive(nums, index, result, list);
        subsetsRecursive(nums, index, result, list2);
    }

    /**
     * Merges overlapping intervals in a 2D array.
     *
     * @param intervals the array of intervals to merge
     * @return the merged intervals as a 2D array
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> resultList = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int[] currentInterval = intervals[0];
        resultList.add(currentInterval);

        for (int i = 1; i < intervals.length; i++) {
            if (overlap(currentInterval, intervals[i])) {
                mergeInterval(currentInterval, intervals[i]);
            } else {
                currentInterval = intervals[i];
                resultList.add(currentInterval);
            }
        }

        return resultList.toArray(new int[resultList.size()][2]);
    }

    /**
     * Checks if two intervals overlap.
     *
     * @param interval1 the first interval
     * @param interval2 the second interval
     * @return true if the intervals overlap, false otherwise
     */
    public boolean overlap(int[] interval1, int[] interval2) {
        return interval2[0] <= interval1[1];
    }

    /**
     * Merges two intervals into a single interval.
     *
     * @param interval1 the first interval (updated with merged result)
     * @param interval2 the second interval
     */
    public void mergeInterval(int[] interval1, int[] interval2) {
        interval1[1] = Math.max(interval1[1], interval2[1]);
    }

    /**
     * Rotates the given matrix 90 degrees clockwise.
     *
     * @param matrix The matrix to be rotated.
     */
    public void rotate(int[][] matrix) {
        transpose(matrix);
        horizontalReverse(matrix);
    }

    /**
     * Transposes the given matrix.
     *
     * @param matrix The matrix to be transposed.
     */
    public void transpose(int[][] matrix) {
        int temp;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * Reverses the elements of each row in the given matrix horizontally.
     *
     * @param matrix The matrix to be horizontally reversed.
     */
    public void horizontalReverse(int[][] matrix) {
        int temp;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }

    /**
     * Determines if it is possible to reach the last index in the given array by jumping from the starting index.
     *
     * @param nums An array of non-negative integers representing the maximum jump length from each position.
     * @return {@code true} if it is possible to reach the last index, {@code false} otherwise.
     */
    public boolean canJump(int[] nums) {
        int goal = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= goal) {
                goal = i;
            }
        }

        return goal == 0;
    }

    /**
     * Returns a list of integers representing the elements of a matrix traversed in spiral order.
     *
     * @param matrix the input matrix
     * @return a list of integers in spiral order
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        int rb = 0;
        int re = matrix.length - 1;
        int cb = 0;
        int ce = matrix[0].length - 1;

        while (rb <= re && cb <= ce) {
            for (int i = cb; i <= ce; i++) {
                result.add(matrix[rb][i]);
            }
            rb++;

            for (int j = rb; j <= re; j++) {
                result.add(matrix[j][ce]);
            }
            ce--;

            if (rb <= re) {
                for (int j = ce; j >= cb; j--) {
                    result.add(matrix[re][j]);
                }
                re--;
            }

            if (cb <= ce) {
                for (int j = re; j >= rb; j--) {
                    result.add(matrix[j][cb]);
                }
                cb++;
            }
        }

        return result;
    }

    /**
     * Given a list of daily temperatures, returns an array where, for each day, tells you how many days
     * you would have to wait until a warmer temperature. If there is no future day for which this is possible,
     * put 0 instead.
     *
     * @param temperatures An array of integers representing daily temperatures.
     * @return An array of integers where the value at index i indicates the number of days you would have to
     *         wait from day i to get a warmer temperature. If there is no future day for which this is possible,
     *         the value is 0.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * Adds two numbers represented as linked lists. Each node in the linked list contains a single digit,
     * and the digits are stored in reverse order, meaning that the 1's digit is at the head of the list.
     *
     * @param l1 The head of the first linked list.
     * @param l2 The head of the second linked list.
     * @return The head of the linked list that represents the sum of the numbers.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            current.next = new ListNode(sum % 10);
            carry = sum / 10;
            current = current.next;
        }

        return dummyHead.next;
    }

    /**
     * Checks if the given string is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * @param s The string to check for palindrome properties.
     * @return true if the pruned string is a palindrome, false otherwise.
     */
    public boolean isPalindrome2(String s) {
        String pruned = "";

        for (int i = 0; i < s.length(); i++) {
            if (isAlphanumeric(s.charAt(i))) {
                pruned = pruned + s.charAt(i);
            }
        }

        pruned = pruned.toLowerCase();
        String reversed = reverse(pruned);

        return reversed.equals(pruned);
    }

    /**
     * Determines if the given character is alphanumeric.
     *
     * @param c The character to check.
     * @return true if the character is alphanumeric, false otherwise.
     */
    private boolean isAlphanumeric(char c) {
        if (('a' <= c && c <= 'z') || ('0' <= c && c <= '9') || ('A' <= c && c <= 'Z')) {
            return true;
        }

        return false;
    }

    /**
     * Reverses the given string.
     *
     * @param s The string to reverse.
     * @return A new string that is the reverse of the given string.
     */
    private String reverse(String s) {
        String reverse = "";

        for (int i = s.length() - 1; i >= 0; i--) {
            reverse = reverse + s.charAt(i);
        }

        return reverse;
    }

    /**
     * Merges the given triplets to see if they can form the specified target.
     *
     * <p>The function attempts to see if by merging the valid triplets
     * (i.e., triplets that don't exceed the target in any dimension),
     * we can obtain the target. A triplet is valid if all its elements
     * are less than or equal to the corresponding elements of the target.
     * </p>
     *
     * @param triplets An array of triplets. Each triplet is an int array of size 3.
     * @param target   The target int array of size 3 that we are trying to match by merging triplets.
     * @return True if by merging the valid triplets we can obtain the target, otherwise False.
     */
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int[] result = new int[3];

        for (int[] triplet : triplets) {
            if (triplet[0] <= target[0] && triplet[1] <= target[1] && triplet[2] <= target[2]) {
                result[0] = Math.max(triplet[0], result[0]);
                result[1] = Math.max(triplet[1], result[1]);
                result[2] = Math.max(triplet[2], result[2]);
            }
        }

        return Arrays.equals(result, target);
    }

    /**
     * Partitions the input string such that each character appears in at most one part.
     * After concatenating all the parts in order, the resultant string should be same as the input string.
     *
     * @param s The input string
     * @return A list of integers, each representing the size of a partition
     */
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        int[] maxIndices = calculateMaxIndices(s);

        int i = 0;
        int j;
        int start = i;

        while (i < s.length()) {
            start = i;
            j = maxIndices[s.charAt(i) - 'a'];
            while (i <= j) {
                j = Math.max(j, maxIndices[s.charAt(i) - 'a']);
                i++;
            }
            result.add(i - start);
        }
        return result;
    }

    /**
     * Calculates the last occurrence indices of each character in the string.
     *
     * @param s The input string
     * @return An array where the i-th element is the last occurrence of the (i+'a')th character in the string.
     */
    private int[] calculateMaxIndices(String s) {
        int[] maxIndices = new int[26];
        for (int i = 0; i < s.length(); i++) {
            maxIndices[s.charAt(i) - 'a'] = i;
        }

        return maxIndices;
    }

    /**
     * Represents a grid of oranges, where the oranges can be either fresh or rotten.
     */
    private int[][] grid;

    /**
     * Calculates the minimum number of minutes until no fresh orange is left.
     * If it's impossible to rot all oranges, returns -1.
     *
     * @param grid 2D matrix where each cell can have the following values:
     *             0: empty cell,
     *             1: cell with a fresh orange,
     *             2: cell with a rotten orange.
     * @return minimum number of minutes until no fresh orange is left or -1 if not possible.
     */
    public int orangesRotting(int[][] grid) {
        this.grid = grid;
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    q.add(new int[] {i, j});
                }
            }
        }

        int minutes = 0;
        int rottenNumberInPreviousMinute;

        while (!q.isEmpty()) {
            rottenNumberInPreviousMinute = q.size();

            for (int i = 0; i < rottenNumberInPreviousMinute; i++) {
                int[] rotten = q.poll();
                if (isValid(rotten[0] - 1, rotten[1])) {
                    grid[rotten[0] - 1][rotten[1]] = 2;
                    q.add(new int[] {rotten[0] - 1, rotten[1]});
                }

                if (isValid(rotten[0] + 1, rotten[1])) {
                    grid[rotten[0] + 1][rotten[1]] = 2;
                    q.add(new int[] {rotten[0] + 1, rotten[1]});
                }

                if (isValid(rotten[0], rotten[1] - 1)) {
                    grid[rotten[0]][rotten[1] - 1] = 2;
                    q.add(new int[] {rotten[0], rotten[1] - 1});
                }

                if (isValid(rotten[0], rotten[1] + 1)) {
                    grid[rotten[0]][rotten[1] + 1] = 2;
                    q.add(new int[] {rotten[0], rotten[1] + 1});
                }
            }
            minutes++;
        }

        if (isLeftAnyNonRotten()) {
            return -1;
        }
        return Math.max(0, minutes - 1);
    }

    /**
     * Checks if the given coordinates are within grid boundaries and contain a fresh orange.
     *
     * @param i Row index.
     * @param j Column index.
     * @return true if the cell contains a fresh orange and is within the grid boundaries; otherwise, false.
     */
    private boolean isValid(int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) {
            return false;
        }
        return true;
    }

    /**
     * Determines if there are any fresh oranges left in the grid.
     *
     * @return true if there are fresh oranges left, false otherwise.
     */
    private boolean isLeftAnyNonRotten() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Computes the weight of the last remaining stone after a series of stone smashing operations.
     * <p>
     * Given an array of stone weights, the method works as follows:
     * 1. Sort the stones in ascending order.
     * 2. Take the heaviest two stones. If they are of different weights, add the difference back to the list.
     * 3. Repeat the above steps until there is only one stone left or no stones left.
     * </p>
     *
     * @param stones An array of integers representing the weights of the stones.
     * @return The weight of the last remaining stone. If no stones remain, returns 0.
     */
    public int lastStoneWeight(int[] stones) {
        List<Integer> stonesList = new ArrayList<>();

        for (int i : stones) {
            stonesList.add(i);
        }

        while (stonesList.size() > 1) {
            Collections.sort(stonesList);
            int heaviest = stonesList.get(stonesList.size() - 1);
            int secondHeaviest = stonesList.get(stonesList.size() - 2);
            stonesList.remove(stonesList.size() - 1);
            stonesList.remove(stonesList.size() - 1);

            if (secondHeaviest < heaviest) {
                stonesList.add(heaviest - secondHeaviest);
            }
        }

        if (stonesList.size() == 1) {
            return stonesList.get(0);
        }

        return 0;
    }

}
