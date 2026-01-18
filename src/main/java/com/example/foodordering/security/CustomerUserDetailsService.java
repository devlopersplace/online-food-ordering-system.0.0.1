package com.example.foodordering.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.foodordering.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Return your User entity directly because it implements UserDetails!
		return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
	}
}

//package com.example.foodordering.security;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//
//import com.example.foodordering.entity.User;
//import com.example.foodordering.repository.UserRepository;
//
//@Service
//public class CustomerUserDetailsService implements UserDetailsService {
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
//		User user = userRepository.findByEmail(email) .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//		
//		return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
//        );
//	}
//}
