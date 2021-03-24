package com.airtel.currencyconverter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.airtel.currencyconverter.controller.form.AuthForm;
import com.airtel.currencyconverter.model.User;
import com.airtel.currencyconverter.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String welcome(Model model) {
		model.addAttribute("registrationForm", new User());
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registrationForm") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("registrationForm", new User());
			return "register";
		}
		User newUser = userService.create(user);
		model.addAttribute("newUser", newUser);
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("loginForm", new AuthForm());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") AuthForm auth, Model model) {
		return "home";
	}

}
