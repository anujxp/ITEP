import java.util.Scanner;

class Employee {
    int id;
    String name;
    double salary;
    String address;

    Employee(int id, String name, double salary, String address) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.address = address;
    }

    void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Address: " + address);
    }
}

public class EmployeeManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee[] empArray = new Employee[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter details for Employee " + (i + 1) + ":");
            int id = sc.nextInt();
            sc.nextLine(); 
            String name = sc.nextLine();
            double salary = sc.nextDouble();
            sc.nextLine(); 
            String address = sc.nextLine();
            empArray[i] = new Employee(id, name, salary, address);
        }

        System.out.println("\nEmployee Details:");
        for (Employee e : empArray) {
            e.display();
        }
        sc.close();
    }
}