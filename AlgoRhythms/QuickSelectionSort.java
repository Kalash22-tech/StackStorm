// File: QuickSelectionSort.java

import java.util.Scanner;

public class QuickSelectionSort {

    // Quick Sort
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // swap
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        // swap pivot
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }

    // Selection Sort
    static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[minIdx]) minIdx = j;

            int temp = arr[i]; arr[i] = arr[minIdx]; arr[minIdx] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n], arr2 = new int[n];
        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++)
            arr[i] = arr2[i] = sc.nextInt();

        System.out.println("\nQuick Sort:");
        quickSort(arr, 0, n - 1);
        for (int val : arr) System.out.print(val + " ");

        System.out.println("\n\nSelection Sort:");
        selectionSort(arr2);
        for (int val : arr2) System.out.print(val + " ");

        sc.close();
    }
}
