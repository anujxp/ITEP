import java.util.Scanner;

public class MoveNegatives {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        rearrange(arr, n);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        sc.close();
    }

    public static void rearrange(int[] arr, int n) {
        int[] temp = new int[n];
        int j = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] >= 0) {
                temp[j++] = arr[i];
            }
        }


        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) {
                temp[j++] = arr[i];
            }
        }


        for (int i = 0; i < n; i++) {
            arr[i] = temp[i];
        }
    }
}