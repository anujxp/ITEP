import java.util.*;

public class ArrayUnion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[m];

        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        for (int i = 0; i < m; i++) b[i] = sc.nextInt();

        findUnion(a, b, n, m);
    }

    public static void findUnion(int[] a, int[] b, int n, int m) {
        int i = 0, j = 0;
        int count = 0;
        int lastAdded = Integer.MIN_VALUE;

        while (i < n && j < m) {
            if (a[i] < b[j]) {
                if (a[i] != lastAdded) {
                    count++;
                    lastAdded = a[i];
                }
                i++;
            } else if (b[j] < a[i]) {
                if (b[j] != lastAdded) {
                    count++;
                    lastAdded = b[j];
                }
                j++;
            } else { // Elements are equal
                if (a[i] != lastAdded) {
                    count++;
                    lastAdded = a[i];
                }
                i++;
                j++;
            }
        }

        // Add remaining elements from array a
        while (i < n) {
            if (a[i] != lastAdded) {
                count++;
                lastAdded = a[i];
            }
            i++;
        }

        // Add remaining elements from array b
        while (j < m) {
            if (b[j] != lastAdded) {
                count++;
                lastAdded = b[j];
            }
            j++;
        }

        System.out.println("Union Count: " + count);
    }
}