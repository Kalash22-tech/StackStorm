class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;

    // Insert at the beginning
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // Insert at the end
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Insert at a given position
    public void insertAtPosition(int data, int position) {
        if (position == 0) {
            insertAtBeginning(data);
            return;
        }
        Node newNode = new Node(data);
        Node current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Position out of bounds!");
            return;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    // Delete by value
    public void deleteByValue(int data) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        if (head.data == data) {
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Element not found!");
            return;
        }
        current.next = current.next.next;
    }

    // Delete by position
    public void deleteByPosition(int position) {
        if (head == null) {
            System.out.println("List is empty!");
            return;
        }
        if (position == 0) {
            head = head.next;
            return;
        }
        Node current = head;
        for (int i = 0; i < position - 1 && current.next != null; i++) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Position out of bounds!");
            return;
        }
        current.next = current.next.next;
    }

    // Search for an element
    public boolean search(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Traverse and print the list
    public void traverse() {
        Node current = head;
        System.out.print("Linked List: ");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("NULL");
    }
}

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        // Insertion
        list.insertAtEnd(10);
        list.insertAtEnd(20);
        list.insertAtBeginning(5);
        list.insertAtPosition(15, 2);

        // Traversal
        list.traverse();  // Output: 5 -> 10 -> 15 -> 20 -> NULL

        // Searching
        System.out.println("Is 15 present? " + list.search(15));  // true
        System.out.println("Is 25 present? " + list.search(25));  // false

        // Deletion
        list.deleteByValue(15);
        list.deleteByPosition(1);

        // Traversal after deletion
        list.traverse();  // Output: 5 -> 20 -> NULL
    }
}