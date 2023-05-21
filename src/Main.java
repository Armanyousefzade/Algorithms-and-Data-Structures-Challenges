import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
