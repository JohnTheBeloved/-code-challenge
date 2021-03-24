package com.airtel.currencyconverter.service;

import java.util.List;

import com.airtel.currencyconverter.controller.form.QueryForm;
import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.User;

public interface UserService {

	User create(User User);

	List<User> get();

	User getByEmailAndPassword(String email, String password) throws ResourceNotFoundException;

	void delete(Long id) throws ResourceNotFoundException;

	QueryForm getQueryForm();

}
