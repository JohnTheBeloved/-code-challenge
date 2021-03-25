package com.airtel.currencyconverter.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.airtel.currencyconverter.controller.form.QueryForm;
import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.User;
import com.airtel.currencyconverter.repository.UserRepository;
import com.airtel.currencyconverter.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@Autowired
	private UserRepository userRepository;

	public User create(User user) {
		User saved = userRepository.save(user);
		return saved;
	}

	public User update(User user) {
		User saved = userRepository.save(user);
		return saved;
	}


	public List<User> get() {
		return userRepository.findAll();
	}

	public void delete(Long userId) throws ResourceNotFoundException {
		User signal = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Signal not found for this id :: " + userId));
		userRepository.delete(signal);
	}

	public QueryForm getQueryForm() {
		QueryForm queryForm = new QueryForm();
		queryForm.setDate(simpleDateFormat.format(new Date()));
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.getQueries().remove("latest");
		queryForm.setQueryHistory(new ArrayList<String>(user.getQueries()));
		return queryForm;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.error("Finding users with %s", username);
		User user = userRepository.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found");
		logger.error("Found User ", username);
		return user;
	}

}
