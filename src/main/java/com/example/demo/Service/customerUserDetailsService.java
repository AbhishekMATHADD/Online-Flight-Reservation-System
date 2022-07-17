package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.demo.Model.User;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.UserRepositoy;

public class customerUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepositoy userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findById(Long.parseLong(username)).get();
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new customerUserDetails(user);
	}

}
