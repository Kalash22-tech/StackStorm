// File: StackOperationsArray_Kalash.java

import java.util.Scanner;

public class StackOperationsArray_Kalash {
    private int top;
    private int maxSize;
    private int[] stack;

    public StackOperationsArray_Kalash(int size) {
        maxSize = size;
        stack = new int[maxSize];
        top = -1;
    }

    // Push operation
    public void push(int value) {
        if (top == maxSize - 1) {
            System.out.println("Stack Overflow!");
        } else {
            stack[++top] = value;
            System.out.println(value + " inserted into the stack.");
        }
    }

    // Pop operation
    public void pop() {
        if (top == -1) {
            System.out.println("Stack Underflow!");
        } else {
            System.out.println(stack[top--] + " deleted from the stack.");
        }
    }

    // Peek operation
    public void peek() {
        if (top == -1) {
            System.out.println("Stack is empty!");
        } else {
            System.out.println("Top element is: " + stack[top]);
        }
    }

    // Traverse operation
    public void traverse() {
        if (top == -1) {
            System.out.println("Stack is empty!");
        } else {
            System.out.println("Stack elements:");
            for (int i = top; i >= 0; i--) {
                System.out.println(stack[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter size of the stack: ");
        int size = sc.nextInt();

        StackOperationsArray_Kalash stack = new StackOperationsArray_Kalash(size);

        while (true) {
            System.out.println("\n--- Stack Menu ---");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Traverse");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
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
                    stack.peek();
                    break;
                case 4:
                    stack.traverse();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
