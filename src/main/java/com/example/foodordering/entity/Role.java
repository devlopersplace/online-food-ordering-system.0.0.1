package com.example.foodordering.entity;

public enum Role {
	USER, ADMIN
}


//
//Step 1: Add the new value to Role.java (shown above)
//Step 2: Update SecurityConfig to define what MODERATOR can access:
		//.requestMatchers("/moderator/**").hasRole("MODERATOR")
//Step 3: Create a way to assign MODERATOR role (e.g., admin panel or special registration endpoint)
//Step 4: Test! Register a user, manually change their role to MODERATOR in the database, then test access


//An enum (enumeration) is a special Java type that represents a fixed set of constants.
// I use it for roles instead of String because it provides compile-time type safety. With a String,
// someone could accidentally assign 'SUPERUSER' or make a typo like 'ADMN', and the code would compile fine but fail at runtime.
// With an enum, only Role.USER and Role.ADMIN are valid - the compiler prevents invalid values.