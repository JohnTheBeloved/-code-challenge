package com.airtel.currencyconverter.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.airtel.currencyconverter.controller.form.QueryForm;
import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.User;

public interface UserService extends UserDetailsService {

	User create(User User);

	User update(User User);

	List<User> get();

	void delete(Long id) throws ResourceNotFoundException;

	QueryForm getQueryForm();

}
