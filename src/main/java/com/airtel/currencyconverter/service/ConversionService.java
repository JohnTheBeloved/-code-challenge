package com.airtel.currencyconverter.service;

import java.util.List;

import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.Conversion;

public interface ConversionService {

	Conversion create(Conversion conversion);

	List<Conversion> get();

	void delete(Long id) throws ResourceNotFoundException;

	boolean save(List<Conversion> currencies);

	Conversion create(String currencyCode, String date, Float amountFrom) throws ResourceNotFoundException;

}
