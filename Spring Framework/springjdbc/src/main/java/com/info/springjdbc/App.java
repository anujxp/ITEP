package com.info.springjdbc;

import java.security.Provider.Service;
import java.util.Scanner;

import com.info.springjdbc.service.StudentService;

public class App 
{
    public static void main( String[] args )
    {	
    	Scanner sc = new Scanner(System.in);
        while(true) {
        System.out.println("press 1 to insert a student");
        System.out.println("press 2 to update teh student");
        System.out.println("press 3 to delete a student");
        System.out.println("Enter 0 to exit.....");
        int choice = sc.nextInt();
        
        switch (choice) {
		case 1:
			StudentService.insert();
			break;
		case 2:
			StudentService.update();
			break;
		case 3:
			StudentService.delete();
			break;
		case 0:
			System.exit(0);

		default:
			break;
		}
        }
    }
}
