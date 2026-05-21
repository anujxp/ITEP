import java.util.Scanner;

public class SelectionSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int arr[] = new int[n];

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < n - 1; i++) {                                                                                                                                                                                                                                                                                                                                                                                                  
            int x = 0;

            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    x = 1;
                }
            }

            if (x == 0) {
                break;
            }
        }

        System.out.println("Sorted array:");
        for(int res:arr) {
            System.out.print(res + " ");
        }
    }
}
// bubble sort
