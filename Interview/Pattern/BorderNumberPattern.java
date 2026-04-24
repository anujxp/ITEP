// 8) Border Number Pattern
// 1 2 3 4 5
// 2       5
// 3       5
// 4       5
// 5 5 5 5 5


public class BorderNumberPattern {
    public static void main(String[] args) {
        int n = 5;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == 1 || j== n)
                 System.out.print(j + " ");
                else if(j==1 || i == n)
                    System.out.print(i + " ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
    }
}
