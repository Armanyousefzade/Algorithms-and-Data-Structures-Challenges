import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
}
