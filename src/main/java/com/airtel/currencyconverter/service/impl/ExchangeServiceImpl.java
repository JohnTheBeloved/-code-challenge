package com.airtel.currencyconverter.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.Currency;
import com.airtel.currencyconverter.model.Exchange;
import com.airtel.currencyconverter.model.User;
import com.airtel.currencyconverter.openexchange.service.OpenExchangeApiService;
import com.airtel.currencyconverter.repository.ExchangeRepository;
import com.airtel.currencyconverter.service.ExchangeService;
import com.airtel.currencyconverter.service.UserService;

@Service
public class ExchangeServiceImpl implements ExchangeService {

	@Autowired
	private ExchangeRepository exchangeRepository;

	@Autowired
	UserService userService;

	@Autowired
	private OpenExchangeApiService openExchangeApiService;

	public Exchange create(Exchange exchange) {
		Exchange saved = exchangeRepository.save(exchange);
		return saved;
	}

	public List<Exchange> get() {
		return exchangeRepository.findAll();
	}

	public void delete(Long exchangeId) throws ResourceNotFoundException {
		Exchange signal =
			exchangeRepository.findById(exchangeId).orElseThrow(() -> new ResourceNotFoundException("Signal not found for this id :: " + exchangeId));
		exchangeRepository.delete(signal);
	}

	@Override
	public boolean save(List<Exchange> exchanges) {
		//TODO: Check if exchange exists in DB
		int noToSave = exchanges.size();
		int noSaved = 0;
		List<Exchange> saved = exchangeRepository.saveAll(exchanges);
		noSaved = saved.size();
		return noToSave == noSaved;
	}

	@Override
	public List<Exchange> getDateExchanges(String date) {
		if (date == null) {
			date = "latest";
		}
		List<Exchange> exchanges = exchangeRepository.findByExchangeDate(date);
		if (exchanges.isEmpty()) {
			openExchangeApiService.pullDateExchangeRates(date);
			exchanges = exchangeRepository.findByExchangeDate(date);
		}
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!user.getQueries().contains(date)) {
			user.getQueries().add(date);
			userService.update(user);
		}
		return exchanges;
	}

	@Override
	public Exchange getDateExchange(Currency currency, String date) throws ResourceNotFoundException {
		List<Exchange> exchanges = getDateExchanges(date);
		Exchange exchange = Optional.of(exchanges)
			.orElseThrow(() -> new ResourceNotFoundException("No exchange found for this date " + date))
			.stream()
			.filter((predicate) -> predicate.getCurrency().getId().equals(currency.getId()))
			.findAny()
			.orElseThrow(() -> new ResourceNotFoundException("No exchange found for this date " + date));
		return exchange;
	}

	@Override
	public List<Exchange> getDateExchanges(String date, Float amount) {
		List<Exchange> exchanges = getDateExchanges(date);
		exchanges.stream().forEach((predicate) -> predicate.setResult(predicate.calculateResult(amount)));
		return exchanges;
	}

}
