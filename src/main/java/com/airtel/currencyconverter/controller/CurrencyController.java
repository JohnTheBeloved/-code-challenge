package com.airtel.currencyconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airtel.currencyconverter.controller.form.QueryForm;
import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.Conversion;
import com.airtel.currencyconverter.service.ConversionService;

@RestController
public class CurrencyController {

	@Autowired
	ConversionService conversionService;

	@GetMapping("/convert")
	public Conversion home(@RequestBody QueryForm queryForm) {
		try {
			return conversionService.create(queryForm.getCurrency(), queryForm.getDate(), queryForm.getAmount());
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
