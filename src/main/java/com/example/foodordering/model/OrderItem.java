package com.example.foodordering.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @ManyToOne
	    @JoinColumn(name = "order_id")
	    private Order order;
	
	@ManyToOne
	@JoinColumn(name = "menu_item_id")
	private MenuItem menuItem;
	
	private int quantity;
	
	public OrderItem() {}
	
	public OrderItem(Order order, MenuItem menuItem, int quantity) {
        this.order = order;
        this.menuItem = menuItem;
        this.quantity = quantity;
    }
	
	  public Long getId() { 
		  return id; 
		  }
	    public Order getOrder() {
	    	return order;
	    	}
	    public MenuItem getMenuItem() {
	    	return menuItem; 
	    	}
	    public int getQuantity() {
	    	return quantity; 
	    	}
}
