import java.util.*;
public class SalaryManagement{
	public static void insertionsort(int[] salaries){
		int n = salaries.length;
		for(int i= 0; i<n; i++){
			int j = i -1;
			int key = salaries[i];
			while(j> 0 && salaries[j] >key){
			salaries[j+1] = salaries[j];
			j -= 1;
			}
			salaries[j+1] = key;			
		}
	}
	
	public static void main(String args[]){

	System.out.println("Enter the number of Employees");
	Scanner sc = new Scanner(System.in);
	int n = sc.nextInt();
	int[] salaries = new int[n];
	System.out.println("Enter the salaries");
	for(int i = 0; i< salaries.length;i++){
		salaries[i] = sc.nextInt();	
	}
	System.out.println("unsorted salaries");
	for(int e : salaries){
	System.out.print(e + " ");
	}
	System.out.println("Sorted salaries with insertionsort");

	insertionsort(salaries);
	for(int e : salaries){
	System.out.print(e + " ");
	}
}
}
