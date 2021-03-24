package com.airtel.currencyconverter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.Conversion;
import com.airtel.currencyconverter.repository.ConversionRepository;
import com.airtel.currencyconverter.service.ConversionService;

// TODO: remove this class
@Service
public class ConversionServiceImpl implements ConversionService {

	@Autowired
	private ConversionRepository exchangeRepository;

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
	public Float create(String currencyCode, Float amount) {
		// TODO Auto-generated method stub
		return null;
	}

}
