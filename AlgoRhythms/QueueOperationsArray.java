// File: QueueOperationsArray.java

import java.util.Scanner;

public class QueueOperationsArray {
    int front = -1, rear = -1;
    int max = 100;
    int[] queue = new int[max];

    public void enqueue(int val) {
        if (rear == max - 1)
            System.out.println("Queue Overflow");
        else {
            if (front == -1) front = 0;
            queue[++rear] = val;
        }
    }

    public void dequeue() {
        if (front == -1 || front > rear)
            System.out.println("Queue Underflow");
        else
            System.out.println("Dequeued: " + queue[front++]);
    }

    public void peek() {
        if (front == -1 || front > rear)
            System.out.println("Queue is Empty");
        else
            System.out.println("Front Element: " + queue[front]);
    }

    public void display() {
        if (front == -1 || front > rear)
            System.out.println("Queue is Empty");
        else {
            System.out.print("Queue Elements: ");
            for (int i = front; i <= rear; i++)
                System.out.print(queue[i] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        QueueOperationsArray q = new QueueOperationsArray();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n1.Enqueue\n2.Dequeue\n3.Peek\n4.Display\n5.Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    q.enqueue(sc.nextInt());
                    break;
                case 2:
                    q.dequeue();
                    break;
                case 3:
                    q.peek();
                    break;
                case 4:
                    q.display();
                    break;
            }
        } while (choice != 5);
        sc.close();
    }
}
