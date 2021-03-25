package com.airtel.currencyconverter.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.airtel.currencyconverter.controller.form.QueryForm;
import com.airtel.currencyconverter.model.Exchange;
import com.airtel.currencyconverter.service.ExchangeService;
import com.airtel.currencyconverter.service.UserService;

@Controller
public class CurrencyController {

	String pattern = "yyyy-MM-dd";
	SimpleDateFormat dataFormat = new SimpleDateFormat(pattern);

	@Autowired
	UserService userService;
	@Autowired
	ExchangeService exchangeService;

	@GetMapping("/home")
	public String home(@RequestParam(name = "date", required = false) String date, Model model) {
		QueryForm queryForm = userService.getQueryForm();
		List<Exchange> exchangeRates = exchangeService.getDateExchanges(date);
		model.addAttribute("queryForm", queryForm);
		model.addAttribute("exchangeRates", exchangeRates);
		return "home";
	}

	@PostMapping("/home")
	public String convert(@Valid @ModelAttribute("queryForm") QueryForm queryFormInput, Model model) {
		QueryForm queryForm = userService.getQueryForm();
		queryForm.setDate(queryFormInput.getDate());
		List<Exchange> exchangeRates = exchangeService.getDateExchanges(queryFormInput.getDate());
		model.addAttribute("queryForm", queryFormInput);
		model.addAttribute("exchangeRates", exchangeRates);
		return "home";
	}

}
