package com.airtel.currencyconverter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.Currency;
import com.airtel.currencyconverter.repository.CurrencyRepository;
import com.airtel.currencyconverter.service.CurrencyService;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;

	public Currency create(Currency currency) {
		Currency saved = currencyRepository.save(currency);
		return saved;
	}

	public List<Currency> get() {
		return currencyRepository.findAll();
	}

	public void delete(Long currencyId) throws ResourceNotFoundException {
		Currency signal =
			currencyRepository.findById(currencyId).orElseThrow(() -> new ResourceNotFoundException("Signal not found for this id :: " + currencyId));
		currencyRepository.delete(signal);
	}

	@Override
	public boolean save(List<Currency> currencies) {
		//TODO: Check if currency exists in DB
		int noToSave = currencies.size();
		int noSaved = 0;
		for (Currency currency : currencies) {
			List<Currency> saved = currencyRepository.saveAll(currencies);
			noSaved = saved.size();
		}
		return noToSave == noSaved;
	}

}
