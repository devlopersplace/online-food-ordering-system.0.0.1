//H2 is a relational database management system(rDBMS) specifically a fast,
/*lightweight, and often in-memory database written in Java.
 *  Its purpose is to store and manage data using SQL. Other examples of databases include MySQL, Oracle, and PostgreSQL.
 *  
 *  Hibernate is an Object-Relational Mapping (ORM) tool.
 *  It is a framework that sits between your Java application code and the database.
 *  Its purpose is to translate Java objects and method calls into SQL queries and database records, eliminating the need for developers to write boilerplate SQL manually. */

package com.example.foodordering.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity // entity tells spring boot to consider it as a spring bean.
@Table(name = "orders")  // By adding table we are actually setting the name of the database where the info of Order is stored
public class Order {

    @Id // it is primary key of the SQL that is it is unique and not null
    @GeneratedValue(strategy = GenerationType.IDENTITY) // this line tells hibernate not to gene
    private Long id;

    private String customerName;

    private final LocalDateTime createdAt;

    private String status; // CREATED, COMPLETED

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public Order() {
        this.createdAt = LocalDateTime.now();
        this.status = "CREATED";
    }

    public Long getId() { 
    	return id;
    	}
    public String getCustomerName() { 
    	return customerName;
    	}
    public LocalDateTime getCreatedAt() { 
    	return createdAt;
    	}
    public String getStatus() { 
    	return status;
    	}
    public List<OrderItem> getItems() { 
    	return items;
    	}

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
