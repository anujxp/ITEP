import java.util.Scanner;

public class PerfectNumberFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter columns: ");
        int cols = sc.nextInt();

        int[][] scores = new int[rows][cols];
        int perfectCount = 0;

        System.out.println("Enter the scores:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                scores[i][j] = sc.nextInt();
            }
        }

        System.out.print("Perfect numbers found: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (isPerfect(scores[i][j])) {
                    System.out.print(scores[i][j] + " ");
                    perfectCount++;
                }
            }
        }

        System.out.println("\nTotal number of perfect scores: " + perfectCount);
        sc.close();
    }

    public static boolean isPerfect(int n) {
        if (n <= 1) return false;
        
        int sum = 1; // 1 is always a divisor for n > 1
        
        // Optimization: only check up to square root
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;
                if (i * i != n) {
                    sum += n / i;
                }
            }
        }
        
        return sum == n;
    }
}