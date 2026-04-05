// <!-- 1.
// You are working as a junior developer for an education-tech company. The company provides digital tools for teachers to analyze students' test scores. A teacher enters the test scores of students into a grid (2D array), where each row represents a student and each column represents a test.

// Your task is to write a Java program that helps the teacher quickly calculate:

// The sum of all even test scores.

// The sum of all odd test scores.

// This helps the teacher understand score patterns more easily for planning further practice sessions. -->

import java.util.Scanner;

public class ScoreCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int students = sc.nextInt();
        System.out.print("Enter number of tests: ");
        int tests = sc.nextInt();

        int[][] scores = new int[students][tests];
        int evenIndexSum = 0;
        int oddIndexSum = 0;

        for (int i = 0; i < students; i++) {
            for (int j = 0; j < tests; j++) {
                scores[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < students; i++) {
            for (int j = 0; j < tests; j++) {
                if (j % 2 == 0) {
                    evenIndexSum += scores[i][j];
                } else {
                    oddIndexSum += scores[i][j];
                }
            }
        }

        System.out.println("Sum of scores at even test indices: " + evenIndexSum);
        System.out.println("Sum of scores at odd test indices: " + oddIndexSum);

        sc.close();
    }
}