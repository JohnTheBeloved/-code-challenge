package com.airtel.currencyconverter.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import com.airtel.currencyconverter.controller.form.QueryForm;
import com.airtel.currencyconverter.exception.ResourceNotFoundException;
import com.airtel.currencyconverter.model.Conversion;
import com.airtel.currencyconverter.model.Exchange;
import com.airtel.currencyconverter.service.ConversionService;
import com.airtel.currencyconverter.service.ExchangeService;
import com.airtel.currencyconverter.service.UserService;

@Controller
public class ConversionController {

	String pattern = "yyyy-MM-dd";
	SimpleDateFormat dataFormat = new SimpleDateFormat(pattern);

	@Autowired
	UserService userService;
	@Autowired
	ExchangeService exchangeService;
	@Autowired
	ConversionService conversionService;

	@GetMapping("/home")
	public String home(@RequestParam(name = "date", required = false) String date, Model model) {
		QueryForm queryForm = userService.getQueryForm();
		List<Exchange> exchangeRates = exchangeService.getDateExchanges(date);
		model.addAttribute("queryForm", queryForm);
		model.addAttribute("exchangeRates", exchangeRates);
		return "home";
	}

	@PostMapping("/home")
	public String convert(@Valid @ModelAttribute("queryForm") QueryForm queryFormInput, BindingResult result, Model model) {
		QueryForm queryForm = userService.getQueryForm();
		List<Exchange> exchangeRates = null;
		try {
			if (!result.hasErrors()) {
				Conversion conversion = conversionService.create(queryFormInput.getCurrency(), queryFormInput.getDate(), queryFormInput.getAmount());
				queryForm.setDate(queryFormInput.getDate());
				queryForm.setAmount(queryFormInput.getAmount());
				queryForm.setResult(conversion.getAmountTo());
				exchangeRates = exchangeService.getDateExchanges(queryFormInput.getDate(), queryForm.amount);
			} else {
				exchangeRates = exchangeService.getDateExchanges(queryFormInput.getDate());
				queryFormInput.setCurrencies(queryForm.getCurrencies());
				queryForm = queryFormInput;
			}
		} catch(HttpClientErrorException | ResourceNotFoundException ex) {
			exchangeRates = exchangeService.getDateExchanges(queryFormInput.getDate());
			queryFormInput.setCurrencies(queryForm.getCurrencies());
			queryForm = queryFormInput;
			model.addAttribute("errorMessage", ex.getMessage());
		}
		model.addAttribute("queryForm", queryForm);
		model.addAttribute("exchangeRates", exchangeRates);
		return "home";

	}

}
