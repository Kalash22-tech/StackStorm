// File Name: StackArrayOperations.java

import java.util.Scanner;

public class StackArrayOperations {
    private int maxSize;
    private int[] stack;
    private int top;

    // Constructor
    public StackArrayOperations(int size) {
        this.maxSize = size;
        this.stack = new int[maxSize];
        this.top = -1;
    }

    // Push operation (Insert)
    public void push(int value) {
        if (top == maxSize - 1) {
            System.out.println("Stack Overflow! Cannot push " + value);
        } else {
            stack[++top] = value;
            System.out.println(value + " pushed to stack.");
        }
    }

    // Pop operation (Delete)
    public void pop() {
        if (top == -1) {
            System.out.println("Stack Underflow! Cannot pop.");
        } else {
            int poppedValue = stack[top--];
            System.out.println(poppedValue + " popped from stack.");
        }
    }

    // Traverse operation
    public void traverse() {
        if (top == -1) {
            System.out.println("Stack is empty.");
        } else {
            System.out.print("Stack elements (top to bottom): ");
            for (int i = top; i >= 0; i--) {
                System.out.print(stack[i] + " ");
            }
            System.out.println();
        }
    }

    // Search operation
    public void search(int value) {
        if (top == -1) {
            System.out.println("Stack is empty.");
            return;
        }
        for (int i = top; i >= 0; i--) {
            if (stack[i] == value) {
                System.out.println(value + " found at position " + (top - i + 1) + " from top.");
                return;
            }
        }
        System.out.println(value + " not found in stack.");
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter stack size: ");
        int size = sc.nextInt();

        StackArrayOperations stack = new StackArrayOperations(size);

        while (true) {
            System.out.println("\n--- Stack Operations ---");
            System.out.println("1. Push (Insert)");
            System.out.println("2. Pop (Delete)");
            System.out.println("3. Traverse");
            System.out.println("4. Search");
            System.out.println("5. Exit");
            System.out.print("Choose operation: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to push: ");
                    int val = sc.nextInt();
                    stack.push(val);
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    stack.traverse();
                    break;
                case 4:
                    System.out.print("Enter value to search: ");
                    int key = sc.nextInt();
                    stack.search(key);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
