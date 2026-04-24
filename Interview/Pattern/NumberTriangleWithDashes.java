// 6) Number Triangle with Dashes
// - - - - 1
// - - - 2 3
// - - 3 4 5
// - 4 5 6 7
// 5 6 7 8 9

class main{
    public static void main(String[] args) {
        int n = 5;
        for (int i = 1; i <= n; i++) {
            int num = i;
            for (int j = 1; j <=n; j++) {
                if(j<=n-i )
                    System.out.print("- ");
                else
                    System.out.print(num++ + " ");
            }
            System.out.println();
        }
    }
}