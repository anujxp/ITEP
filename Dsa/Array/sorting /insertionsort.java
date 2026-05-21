import java.util.Scanner;

class insertionsort{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of Employees ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        System.out.println("Enter the salaries...");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i-1;

            while(j>= 0 && (arr[j] > key)){
                arr[j+1 ] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        for( int i : arr)
        {
            System.out.print(" " + i);
        }
        System.out.println();
    }
}