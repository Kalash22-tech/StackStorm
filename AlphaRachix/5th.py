def quick_sort(arr):
    # Base case
    if len(arr) <= 1:
        return arr
    # Choosing pivot and dividing list
    pivot = arr[0]
    left = [x for x in arr[1:] if x <= pivot]
    right = [x for x in arr[1:] if x > pivot]
    return quick_sort(left) + [pivot] + quick_sort(right)

def selection_sort(arr):
    # Loop through array
    for i in range(len(arr)):
        min_index = i
        # Find the minimum in remaining array
        for j in range(i+1, len(arr)):
            if arr[j] < arr[min_index]:
                min_index = j
        # Swap
        arr[i], arr[min_index] = arr[min_index], arr[i]
    return arr

# Main code
arr = [64, 25, 12, 22, 11]

print("Original Array:", arr)
print("Quick Sort Result:", quick_sort(arr.copy()))
print("Selection Sort Result:", selection_sort(arr.copy()))
