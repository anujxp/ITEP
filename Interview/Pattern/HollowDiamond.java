// 9) Hollow Diamond Square
// ***********
// ****   ****
// ***     ***
// **       **
// *         *
// *         *
// **       **
// ***     ***
// ****   ****
// ***********

public class HollowDiamond {
    public static void main(String[] args) {
        int n = 5;
        for (int i = 1; i <= 2*n; i++) {
            for (int j = 1; j <= 2*n+1; j++) {
                if(i==1|| i==2*n||j <= n-i+1|| j> n+i || j <= i-n || j > 3 * n - i + 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }
}


class HollowDiamond1 {
    public static void main(String[] args) {
        int n = 5;

        // Total rows: 2*n, Total columns: 2*n
        for (int i = 1; i <= 2 * n; i++) {
            // Transform i to be symmetrical (1,2,3,4,5,5,4,3,2,1)
            int row = i <= n ? i : 2 * n - i + 1;

            for (int j = 1; j <= 2 * n; j++) {
                // Logic: Print star if j is in the left zone OR right zone
                // Left zone: j <= n - row + 1
                // Right zone: j > n + row - 1
                if (j <= (n - row + 1) || j >= (n + row)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}


class HollowDiamond2 {
    public static void main(String[] args) {
        int n = 5;

        // Top half of the pattern
        for (int i = 0; i < n; i++) {
            // Left stars: decreases as i increases
            for (int j = 1; j <= n - i; j++) System.out.print("*");
            // Middle spaces: increases as i increases
            for (int j = 1; j <= 2 * i; j++) System.out.print(" ");
            // Right stars: decreases as i increases
            for (int j = 1; j <= n - i; j++) System.out.print("*");
            System.out.println();
        }

        // Bottom half of the pattern
        for (int i = 0; i < n; i++) {
            // Left stars: increases as i increases
            for (int j = 1; j <= i + 1; j++) System.out.print("*");
            // Middle spaces: decreases as i increases
            for (int j = 1; j <= 2 * (n - i - 1); j++) System.out.print(" ");
            // Right stars: increases as i increases
            for (int j = 1; j <= i + 1; j++) System.out.print("*");
            System.out.println();
        }
    }
}