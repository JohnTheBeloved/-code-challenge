package com.airtel.currencyconverter.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.airtel.currencyconverter.controller.form.QueryForm;
import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.Currency;
import com.airtel.currencyconverter.model.User;
import com.airtel.currencyconverter.repository.UserRepository;
import com.airtel.currencyconverter.service.CurrencyService;
import com.airtel.currencyconverter.service.UserService;
import com.airtel.currencyconverter.util.CurrencyUtil;

@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CurrencyService currencyService;

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
		List<Currency> currencies =
			currencyService.get().stream().filter((predicate) -> !predicate.getCode().equals(CurrencyUtil.DOLLAR)).collect(Collectors.toList());
		logger.trace("Number of currencies found in database " + currencies.size());
		queryForm.setCurrencies(currencies);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.getQueries().remove("latest");
		queryForm.setQueryHistory(new ArrayList<String>(user.getQueries()));
		return queryForm;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.trace("Finding users with %s", username);
		User user = userRepository.findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found");
		logger.trace("Found User ", username);
		return user;
	}

}
