# Linear Search
def linear_search(arr, key):
    for i in range(len(arr)):
        if arr[i] == key:
            return i
    return -1

# Binary Search (list must be sorted)
def binary_search(arr, key):
    arr.sort()  # Make sure array is sorted
    low = 0
    high = len(arr) - 1
    while low <= high:
        mid = (low + high) // 2
        if arr[mid] == key:
            return mid
        elif arr[mid] < key:
            low = mid + 1
        else:
            high = mid - 1
    return -1

# Main code
arr = [5, 3, 9, 1, 7]
key = int(input("Enter number to search: "))

# Linear Search
index1 = linear_search(arr, key)
print("Linear Search: Found at index" if index1 != -1 else "Linear Search: Not found")

# Binary Search
index2 = binary_search(arr, key)
print("Binary Search: Found at index" if index2 != -1 else "Binary Search: Not found")
