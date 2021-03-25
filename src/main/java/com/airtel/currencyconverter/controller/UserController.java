package com.airtel.currencyconverter.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.airtel.currencyconverter.model.User;
import com.airtel.currencyconverter.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public String welcome(Model model) {
		model.addAttribute("registrationForm", new User());
		return "register";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("registrationForm") User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("registrationForm", user);
			return "register";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPasswordConfirmation(user.getPassword()); //FIXME: Remove this from model

		User newUser = userService.create(user);
		model.addAttribute("newUser", newUser);

		return "redirect:/home";
	}

	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpSession session) {
		session.setAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		return "login";
	}

	private String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		if(exception != null) {
			exception.printStackTrace();
		}
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}
		return error;
	}
}
