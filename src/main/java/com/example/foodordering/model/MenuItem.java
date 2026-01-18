package com.example.foodordering.model;


//brings in the entire suite of annotations and interfaces for the Jakarta Persistence API (JPA).
import jakarta.persistence.*;
//Think of JPA as a "rulebook" or a standard set of instructions. It tells Java how to talk.
//to a database without you having to write raw SQL for every single operation.

@Entity //It is a marker annotation which tells the JPA provider like Hibernate that this
//java class should be tracked and mapped to a database table.
//without this the class is just a POJO. With it, Hibernate knows it can save,update or delete instances of this class in the database.

@Table(name = "menu_items")
public class MenuItem {
	
	@Id  //This marks the id field as the unique identifier for the entity.(Primary key as for SQL)http://localhost:8080/api/menu
	// without this JPA will throw an error because it wouldn't know which row to update or delete.
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*IDENTITY tells Hibernate to let the database handle the ID generation
	 * it ensures that your Java code doesn't have to look for next available ID
	 * the database does it automatically when you "Save*/
	
	//Other strategies mention SEQUENCE(used in Oracle) or UUID(if you want a long string instead of a number).
	
	private Long id; //here We use the Wrapper class Long instead of the primitive long.
	
	@Column(nullable = false) //This is a Schema-level constraint. It tells the database: "You cannot save a menu item if the name is missing."
	private String name;
	
	private double price;
	private String category;
	
	public MenuItem() {}
	
	public MenuItem(String name, double price, String category) {
		this.name = name;
		this.price = price;
		this.category = category;
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public String getCategory() {
		return category;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}

/* The above code was throwing errors in few places:
 * Fix include adding two dependency in the pom.xml file one being:-
 * org.springframework.boot
 * com.h2database
 * then we updated the project step included:
 * right click on main project folder
 * click on Maven
 * update project-> force update 
 * finish*/
