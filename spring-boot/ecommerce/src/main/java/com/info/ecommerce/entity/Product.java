package com.info.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String brand;
	private String price;

	@ManyToOne
	@JoinColumn(name = "catey_id")
	private Category category;

	@OneToMany(mappedBy = "product")
	private List<CartItem> cartItems;

	
}
