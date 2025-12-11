package com.info.jainzbitesproject.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="foods")
public class Food {
     
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private double price;
	
	@Column(length = 200)
	private String description;
	
	private String image;
	
	
	@ManyToOne
	@JoinColumn(name="categories_id")
	private Category category;
	
	@OneToMany(mappedBy = "food",cascade = CascadeType.ALL)
	private List<OrderItem> order_Items;
	
	@OneToMany(mappedBy = "food",cascade = CascadeType.ALL)
	private List<Cart> cartItems;
	
	
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<OrderItem> getOrder_Items() {
		return order_Items;
	}

	public void setOrder_Items(List<OrderItem> order_Items) {
		this.order_Items = order_Items;
	}

	public List<Cart> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<Cart> cartItems) {
		this.cartItems = cartItems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
}

