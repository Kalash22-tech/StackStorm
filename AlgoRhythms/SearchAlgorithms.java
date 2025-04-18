public class SearchAlgorithms {

    // Linear Search implementation
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return index if target found
            }
        }
        return -1; // Return -1 if target not found
    }

    // Binary Search implementation (iterative)
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // Target found
            } else if (arr[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        return -1; // Target not found
    }

    // Binary Search implementation (recursive)
    public static int binarySearchRecursive(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1; // Base case: target not found
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursive(arr, target, mid + 1, right);
        } else {
            return binarySearchRecursive(arr, target, left, mid - 1);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        int target = 23;

        // Linear Search
        int linearResult = linearSearch(numbers, target);
        System.out.println("Linear Search:");
        if (linearResult != -1) {
            System.out.println("Element found at index: " + linearResult);
        } else {
            System.out.println("Element not found in the array");
        }

        // Binary Search (iterative)
        int binaryResult = binarySearch(numbers, target);
        System.out.println("\nBinary Search (Iterative):");
        if (binaryResult != -1) {
            System.out.println("Element found at index: " + binaryResult);
        } else {
            System.out.println("Element not found in the array");
        }

        // Binary Search (recursive)
        int binaryRecursiveResult = binarySearchRecursive(numbers, target, 0, numbers.length - 1);
        System.out.println("\nBinary Search (Recursive):");
        if (binaryRecursiveResult != -1) {
            System.out.println("Element found at index: " + binaryRecursiveResult);
        } else {
            System.out.println("Element not found in the array");
        }
    }
}