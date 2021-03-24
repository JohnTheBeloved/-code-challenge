package com.airtel.currencyconverter.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.User;
import com.airtel.currencyconverter.repository.UserRepository;
import com.airtel.currencyconverter.service.UserService;

@Service
public class UserServiceImpl implements UserService {

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

}
