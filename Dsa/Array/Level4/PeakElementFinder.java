import java.util.Scanner;

public class PeakElementFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(findPeakIndex(arr, n));
        sc.close();
    }

    public static int findPeakIndex(int[] arr, int n) {
        // Handle single element case
        if (n == 1) return 0;
        
        // Check if the first element is a peak
        if (arr[0] >= arr[1]) return 0;
        
        // Check if the last element is a peak
        if (arr[n - 1] >= arr[n - 2]) return n - 1;

        // Binary Search for middle elements
        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // If mid is a peak
            if (arr[mid] >= arr[mid - 1] && arr[mid] >= arr[mid + 1]) {
                return mid;
            }
            
            // If the left neighbor is greater, a peak must exist on the left
            if (arr[mid - 1] > arr[mid]) {
                high = mid - 1;
            } 
            // Otherwise, a peak must exist on the right
            else {
                low = mid + 1;
            }
        }
        return -1;
    }
}