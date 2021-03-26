package com.airtel.currencyconverter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.Conversion;
import com.airtel.currencyconverter.model.Currency;
import com.airtel.currencyconverter.model.Exchange;
import com.airtel.currencyconverter.model.User;
import com.airtel.currencyconverter.repository.ConversionRepository;
import com.airtel.currencyconverter.service.ConversionService;
import com.airtel.currencyconverter.service.CurrencyService;
import com.airtel.currencyconverter.service.ExchangeService;

// TODO: remove this class
@Service
public class ConversionServiceImpl implements ConversionService {

	@Autowired
	private ConversionRepository exchangeRepository;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private ExchangeService exchangeService;

	public Conversion create(Conversion exchange) {
		Conversion saved = exchangeRepository.save(exchange);
		return saved;
	}

	public List<Conversion> get() {
		return exchangeRepository.findAll();
	}

	public void delete(Long exchangeId) throws ResourceNotFoundException {
		Conversion signal =
			exchangeRepository.findById(exchangeId).orElseThrow(() -> new ResourceNotFoundException("Signal not found for this id :: " + exchangeId));
		exchangeRepository.delete(signal);
	}

	@Override
	public boolean save(List<Conversion> exchanges) {
		//TODO: Check if exchange exists in DB
		int noToSave = exchanges.size();
		int noSaved = 0;
		List<Conversion> saved = exchangeRepository.saveAll(exchanges);
		noSaved = saved.size();
		return noToSave == noSaved;
	}

	@Override
	public Conversion create(String currencyCode, String date, Float amountFrom) throws ResourceNotFoundException {
		Currency currencyTo = currencyService.getByCode(currencyCode);
		Currency currencyFrom = currencyService.getBaseCurrency();
		Exchange exchange = exchangeService.getDateExchange(currencyTo, date);
		Float amountTo = (float) (exchange.getRate() * amountFrom);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Conversion conversion = new Conversion(currencyFrom, currencyTo, amountFrom, amountTo);
		conversion.setUser(user);
		conversion.setExchangeDate(date);
		conversion.setAmountTo(amountTo);
		create(conversion);
		return conversion;
	}

}
