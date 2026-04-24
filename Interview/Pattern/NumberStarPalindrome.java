// 5) Number-Star Palindrome
// 12344321
// 123**321
// 12****21
// 1******1

public class NumberStarPalindrome {
    public static void main(String[] args) {
        int n = 4;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i + 1; j++) {
                System.out.print(j);
            }
            for (int j = 1; j < 2 * i - 1; j++) {
                System.out.print("*");
            }
            for (int j = n - i + 1; j >= 1; j--) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
}
