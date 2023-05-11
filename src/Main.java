import java.util.HashSet;
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
}
