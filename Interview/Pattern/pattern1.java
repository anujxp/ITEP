// * * * * * 6
// * * * * 6 5 6
// * * * 6 5 4 5 6
// * * 6 5 4 3 4 5 6
// * 6 5 4 3 2 3 4 5 6
// 6 5 4 3 2 1 2 3 4 5 6
public class pattern1 {

    public static void main(String[] args) {
        int n = 6;
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= n-i; j++) {
                System.out.print("* ");
            }
            int c = n;
            for (int j = 1; j <= i*2-1; j++) {
                System.out.print(c + " ");
                c = j<i ? c-1 : c+1;
            }
            System.out.println();
        }
    }
}
