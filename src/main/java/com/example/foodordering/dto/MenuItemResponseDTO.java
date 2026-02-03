//Used when the server sends data back to the client (e.g., showing menu items).
//Includes id because the server generates and returns it.

package com.example.foodordering.dto;

public class MenuItemResponseDTO {
	private Long id;
    private String name;
    private double price;
    private String category;

    public MenuItemResponseDTO(Long id, String name, double price, String category) {
        this.id = id;
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
}
