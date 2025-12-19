package com.info.bootjpa;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.info.bootjpa.entity.Product;
import com.info.bootjpa.repo.ProductRepo;
import com.info.bootjpa.service.ProductService;

@SpringBootApplication
public class BootjpaApplication {

    private final ProductRepo productRepo;

    BootjpaApplication(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

	public static void main(String[] args) {
		ApplicationContext context =   SpringApplication.run(BootjpaApplication.class, args);
		ProductService productService = context.getBean(ProductService.class);
        while(true){
            System.out.println("press 1 for insert");
            System.out.println("press 2 for update");
            System.out.println("press 3 for delete");
            System.out.println("press 4 for retrieve by id ");;
            System.out.println("press 5 for retrive all ...");
            System.out.println("press 0 for exit....");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice){
                case 1 :
                    save();
                    break;
                case 2:

                    System.out.println("Enter id to update...");
                    Product p1  = productService.productById(sc.nextInt());
                    System.out.println("your product name is...");

            }



        }

		Product p = new Product();
		p.setTitle("Samsung f41");
  		p.setBrand("Samsung");
		p.setPrice(25000);
		p.setDiscount(5.5f);
		 Product dbProduct =  productService.saveProduct(p);
		 if(dbProduct!=null)
			 System.out.println("Product saved..");
		 else
			 System.out.println("Not saved...");
		 
		 
		 List<Product>list =  productService.getProductList();
		 for(Product p1 : list)
			 System.out.println(p1.getTitle()+"  "+p1.getBrand());
	}
    public static void save(){
        Product p = new Product();
        System.out.println("Enter title ");
        p.setTitle(sc.next());
        System.out.println("Enter brand...");
        p.setBrand(sc.next());
        System.out.println("Enter price...");
        p.setPrice(sc.nextFloat());
        System.out.println("Enter discount percentage..");
        p.setDiscount(sc.nextFloat());
        productService.saveProduct(p);
    }


}
