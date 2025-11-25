package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Passport passport;
	
	public User(int id, String name, String address, Passport passport) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.passport = passport;
	}
	
	public User(String name, String address, Passport passport) {
		super();
		this.name = name;
		this.address = address;
		this.passport = passport;
	}

	public User() {
		super();
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Passport getPassport() {
		return passport;
	}
	public void setPassport(Passport passport) {
		this.passport = passport;
	}
	
	
	
	
	
}
