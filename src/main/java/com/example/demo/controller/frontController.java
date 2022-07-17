package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;

@Controller

public class frontController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String viewHomePage() {
		return "frontpage";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(User user) {
		User u = userService.saveWithDefaultUser(user);
		return "redirect:/getId/" + u.getId();
	}

	@GetMapping("/getId/{id}")
	public String getUserID(@PathVariable(name = "id") Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		System.out.println(user.getId());
		return "register_success";

	}

	@GetMapping("demo")
	public String getIn() {
		return "index";
	}

}
