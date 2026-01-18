/* Imagine you’re ordering food online. You tell the app:

I want a Pizza

It costs 200 rupees 

It belongs to the category ‘Italian’

All those little pieces of information (name, price, category) need to travel from your phone → to the server → to the database.

A DTO is like a box 📦 that neatly packs those details together so they can be sent safely from one place to another.*/

package com.example.foodordering.dto;

public class MenuItemRequestDTO {

    private String name;
    private double price;
    private String category;

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

//Used when the client sends data to the server (e.g., creating a new menu item).
//Doesn’t include id because the client doesn’t decide IDs.

