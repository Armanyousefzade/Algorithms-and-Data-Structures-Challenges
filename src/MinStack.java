import java.util.ArrayList;
import java.util.List;

class MinStack {

    List<Integer> stack;
    List<Integer> minStack;

    /**
     * Initializes a new instance of the MinStack class.
     */
    public MinStack() {
        this.stack = new ArrayList<>();
        this.minStack = new ArrayList<>();
    }

    /**
     * Pushes an element onto the stack.
     *
     * @param val The value to be pushed onto the stack.
     */
    public void push(int val) {
        stack.add(val);
        if (minStack.isEmpty()) {
            minStack.add(val);
        } else {
            minStack.add(Math.min(val, minStack.get(minStack.size() - 1)));
        }
    }

    /**
     * Removes the topmost element from the stack.
     */
    public void pop() {
        stack.remove((int) stack.size() - 1);
        minStack.remove((int) minStack.size() - 1);
    }

    /**
     * Returns the value of the topmost element in the stack.
     *
     * @return The value of the topmost element.
     */
    public int top() {
        return stack.get(stack.size() - 1);
    }

    /**
     * Returns the minimum value in the stack.
     *
     * @return The minimum value in the stack.
     */
    public int getMin() {
        return minStack.get(stack.size() - 1);
    }

    public int search(int[] nums, int target) {
        return search(0, nums.length - 1, nums, target);
    }

    /**
     * Performs a binary search to find the target element in the given array.
     *
     * @param left  The left index of the search range.
     * @param right The right index of the search range.
     * @param nums  The array in which to perform the search.
     * @param target The target element to find.
     * @return The index of the target element if found, otherwise -1.
     */
    private int search(int left, int right, int[] nums, int target) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;

        if (nums[mid] == target) {
            return mid;
        }

        if (target < nums[mid]) {
            return search(left, mid - 1, nums, target);
        } else {
            return search(mid + 1, right, nums, target);
        }
    }

    /**
     * Determines whether a linked list has a cycle.
     *
     * @param head The head node of the linked list.
     * @return {@code true} if a cycle is present in the linked list, {@code false} otherwise.
     */
    public boolean hasCycle(ListNode head) {
        ListNode current = head;

        while (head != null && head.next != null) {
            if (current.next == head.next.next) {
                return true;
            }

            head = head.next.next;
            current = current.next;
        }

        return false;
    }
}
