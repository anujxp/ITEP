
// Given an array of N integers, and an integer K, find the number of pairs of elements in the array whose sum is equal to K.
// //

import java.util.Scanner;

class Sum{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter arraay size : ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter Elements...");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Enter target sum..");
        n = sc.nextInt();
        int count = findPair(arr, n);
        System.out.println(count);
    }
    private static int findPair(int[] arr,int n){
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] + arr[j] == n)
                    count++;
            }
        }


        return count;
        


    }
}