import java.util.Scanner;

public class CyclicRotate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter arraay size : ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter Elements...");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        rotate(arr);
        for (int i : arr) {
            System.out.print(i+ " ");
        }
        System.out.println();
    }
    private static void rotate(int[] arr){
        int temp = arr[0];
        for (int i = 0; i < arr.length-1; i++) {
            arr[i] = arr[i+1];          
        }
        arr[arr.length-1] = temp;
    }
}
