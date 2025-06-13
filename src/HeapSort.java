import java.util.Arrays;

// Question 3
public class HeapSort {

    // Swap counter
    private int countSwap;

    // Swap utility with counter
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        countSwap++;
    }

    // Heapify down (used by both heapifyBuild and heapSort)
    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    // Method 1: Build heap using heapify
    public int buildHeapHeapify(int[] arr) {
        countSwap = 0;
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        return countSwap;
    }

    // Heap insert upward per element
    private void heapifyUp(int[] arr, int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (arr[index] > arr[parent]) {
                swap(arr, index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    // Method 2: Build heap by adding one by one
    public int buildHeapOneByOne(int[] input) {
        countSwap = 0;
        int[] arr = new int[input.length];
        int size = 0;

        for (int i = 0; i < input.length; i++) {
            arr[size] = input[i];
            heapifyUp(arr, size);
            size++;
        }

        // Copy back to input array for sorting
        for (int i = 0; i < input.length; i++) {
            input[i] = arr[i];
        }

        return countSwap;
    }

    // Heap sort and count amount of swaps
    public int heapSort(int[] arr) {
        countSwap = 0;
        int n = arr.length;

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
        return countSwap;
    }
    private int[] copyArray(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    // Run both methods 1 and 2
    public void run(int[] input) {

        // Heapify Swap Count
        // Total Swap Count

        // Method 1
        int[] arr1 = copyArray(input);
        int heapifySwaps1 = buildHeapHeapify(arr1);
        int sortSwaps1 = heapSort(arr1);
        System.out.println("1 " + heapifySwaps1);
        System.out.println("1 " + (heapifySwaps1 + sortSwaps1));

        // Method 2
        int[] arr2 = copyArray(input);
        int heapifySwaps2 = buildHeapOneByOne(arr2);
        int sortSwaps2 = heapSort(arr2);
        System.out.println("2 " + heapifySwaps2);
        System.out.println("2 " + (heapifySwaps2 + sortSwaps2));
    }
}
