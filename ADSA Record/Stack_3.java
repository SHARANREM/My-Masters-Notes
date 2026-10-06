class Stack<T> {

    T[] stack;
    int top = -1;

    Stack(int size) {
        stack = (T[]) new Object[size];
    }

    void push(T item) {
        if (top == stack.length - 1)
            System.out.println("Stack Overflow");
        else
            stack[++top] = item;
    }

    T pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return null;
        }

        return stack[top--];
    }

    T peek() {
        if (top == -1)
            return null;

        return stack[top];
    }

    void display() {
        if (top == -1) {
            System.out.println("Stack is Empty");
            return;
        }

        for (int i = top; i >= 0; i--)
            System.out.println(stack[i]);
    }
}

public class Stack_3 {

    public static void main(String[] args) {

        Stack<Integer> s = new Stack<>(5);

        s.push(10);
        s.push(20);
        s.push(30);

        System.out.println("Stack:");
        s.display();

        System.out.println("Top element: " + s.peek());

        System.out.println("Popped element: " + s.pop());

        System.out.println("Stack after pop:");
        s.display();
    }
}
