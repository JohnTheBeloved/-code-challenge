package com.airtel.currencyconverter.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtel.currencyconverter.controller.form.QueryForm;
import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.User;
import com.airtel.currencyconverter.repository.UserRepository;
import com.airtel.currencyconverter.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@Autowired
	private UserRepository userRepository;

	public User create(User user) {
		User saved = userRepository.save(user);
		return saved;
	}

	public List<User> get() {
		return userRepository.findAll();
	}

	public User getByEmailAndPassword(String email, String password) throws ResourceNotFoundException {
		return Optional.of(userRepository.findByEmailAndPassword(email, password))
			.orElseThrow(() -> new ResourceNotFoundException("username or password not correct :: " + email));
	}

	public void delete(Long userId) throws ResourceNotFoundException {
		User signal = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Signal not found for this id :: " + userId));
		userRepository.delete(signal);
	}

	public QueryForm getQueryForm() {
		QueryForm queryForm = new QueryForm();
		queryForm.setDate(simpleDateFormat.format(new Date()));
		queryForm.setQueryHistory(
			Arrays.asList(
				"2020-03-02",
				"2020-05-05",
				"2020-06-15",
				"2020-06-22",
				"2020-06-21",
				"2020-06-20",
				"2020-06-19",
				"2020-06-18",
				"2020-06-17",
				"2020-06-16"));
		return queryForm;
	}

}
