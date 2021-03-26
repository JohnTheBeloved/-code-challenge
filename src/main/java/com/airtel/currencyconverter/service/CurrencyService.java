package com.airtel.currencyconverter.service;

import java.util.List;

import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.Currency;

public interface CurrencyService {

	Currency create(Currency currency);

	List<Currency> get();

	Currency getBaseCurrency();

	Currency getByCode(String code);

	void delete(Long id) throws ResourceNotFoundException;

	boolean save(List<Currency> currencies);

}
