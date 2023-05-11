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
}
