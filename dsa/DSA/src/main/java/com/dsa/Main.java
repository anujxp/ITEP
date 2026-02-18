package com.dsa;

import org.jetbrains.annotations.NotNull;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void selectionSort(int arr[]){
        int n = arr.length;
        int minIndex;
        for (int i = 0; i <n; i++) {
            minIndex = i;
            for (int j = i+1; j <n ; j++) {
                if(arr[minIndex] > arr[j])
                    minIndex = j;
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            System.out.println(arr[i]);
        }
    }
    public static void insertionSort( int[] arr){
        int n = arr.length;
        int i,j,key;
        for ( i = 1; i < n; i++) {
            key = arr[i];
            j=i-1;
            while(key < arr[j] && j>=0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }

        
    }
    public static void main(String[] args) {
        int arr[] = {1,4,2,5,9,6,7};
        System.out.println("before sorting");
        
        for(int s : arr) System.out.print(s+ " ");
        
        selectionSort(arr);
        System.out.println("\nafter sorting");
        
        for(int s : arr) System.out.print(s+ " ");
        


   }
}