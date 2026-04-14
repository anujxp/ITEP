
import java.util.Scanner;

public class Bubblesort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter arraay size : ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter Elements...");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        bubbleSort(arr);
        for (int i : arr) {
            System.out.print(i+ " ");
        }
        System.out.println();
    }
    public static int[] bubbleSort(int[] arr){
        for (int i = 0; i < arr.length-1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                   flag = false;
                }
                if(flag) break;
            }
        }
        return arr;
    }
    
}
