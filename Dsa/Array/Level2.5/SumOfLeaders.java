// 1.
// Sum of leaders in an array

// Problem Statement
// A function accepts a positive integer array ‘arr’ of size ‘n’ as its argument. Implement the function to find the leaders in the array and return their sum. An element is a leader in the array if it is greater than all the elements to its right side. The rightmost element is always a leader.

// Note
// If ‘arr’ is empty or none(in case of python), return -1
// Output lies within the integer range.

// Example
// Input
// 7
// 52 66 64 36 45 24 32
// Output
// 207

import java.util.Scanner;

public class SumOfLeaders {
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(System.in)){
            
        System.out.println("Enter numbers of Element...");
        int n = sc.nextInt();
        System.out.println("Enter elements...");
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            System.out.println("Enter elemets at index " + i);
            arr[i] = sc.nextInt();
        }
        System.out.println(sum(arr));
        }

        
    }
    public static int sum(int[] arr){
        int sum = 0;
        if(arr.length==0) return -1;
        for (int i = 0; i < arr.length; i++) {
            boolean isLeader = true;
            for (int j = i; j < arr.length; j++) {
                if(arr[i]<=arr[j]){
                    isLeader = false;
                    break;
                }
            }
            if(isLeader) sum+=arr[i];
        }
        return sum;
    }
}




class SumOfLeaders1 {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter number of elements:");
            if (!sc.hasNextInt()) return;
            int n = sc.nextInt();
            
            int[] arr = new int[n];
            System.out.println("Enter elements:");
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            
            System.out.println("Sum of leaders: " + findSumOfLeaders(arr));
        }
    }

    public static int findSumOfLeaders(int[] arr) {
        // Handle constraint: return -1 if array is empty
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int n = arr.length;
        // The rightmost element is always a leader
        int currentMax = arr[n - 1];
        int sum = currentMax;

        // Traverse the array from right to left (starting from second-to-last)
        for (int i = n - 2; i >= 0; i--) {
            // If current element is strictly greater than all elements to its right
            if (arr[i] > currentMax) {
                sum += arr[i];
                currentMax = arr[i]; // Update the max to the current leader
            }
        }

        return sum;
    }
}