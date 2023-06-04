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
}
