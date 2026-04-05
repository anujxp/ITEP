import java.util.Scanner;
import java.util.HashMap;

public class UniqueElementsSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter array length: ");
        int length = sc.nextInt();
        int[] arr = new int[length];
        
        System.out.println("Enter elements:");
        for (int i = 0; i < length; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Average (Brute Force) Sum: " + averageSumUnique(arr, length));
        System.out.println("Best (HashMap) Sum: " + bestSumUnique(arr, length));
        
        sc.close();
    }

    public static int averageSumUnique(int[] arr, int length) {
        if (length == 0) return -1;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < length; j++) {
                if (i != j && arr[i] == arr[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                sum += arr[i];
            }
        }
        return sum;
    }

    public static int bestSumUnique(int[] arr, int length) {
        if (length == 0) return -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : arr) {
            if (map.get(num) == 1) {
                sum += num;
            }
        }
        return sum;
    }
}