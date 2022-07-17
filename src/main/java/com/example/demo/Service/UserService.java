package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Role;
import com.example.demo.Model.User;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepositoy;

@Service
public class UserService {

	@Autowired
	private UserRepositoy userRepo;

	@Autowired
	private RoleRepository roleRepo;

	public User getUserById(Long id) {
		return userRepo.findById(id).get();

	}

	public User saveWithDefaultUser(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		Role role = roleRepo.findByName("User");
		user.addRole(role);
		return userRepo.save(user);
	}

}
