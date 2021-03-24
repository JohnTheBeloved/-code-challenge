package com.airtel.currencyconverter.openexchange.service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.airtel.currencyconverter.model.Currency;
import com.airtel.currencyconverter.model.Exchange;
import com.airtel.currencyconverter.openexchange.model.ExchangeHistory;
import com.airtel.currencyconverter.service.CurrencyService;
import com.airtel.currencyconverter.service.ExchangeService;

@Service
public class OpenExchangeService {

	private static Logger logger = LoggerFactory.getLogger(OpenExchangeService.class);

	@Value("${openexchange.appid}")
	private String appId;

	@Value("${openexchange.apipath.base}")
	private String basePath;

	@Value("${openexchange.apipath.latest}")
	private String latestExchangePath;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private ExchangeService exchangeService;

	String pattern = "yyyy-MM-dd";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@PostConstruct
	public void init() {
		createCurrencies();
		pullLatestExchangeRates();
	}

	public void createCurrencies() {
		Currency euro = new Currency("Euro", "Europe", "EUR");
		Currency usDollar = new Currency("US Dollar", "Nigeria", "USD");
		Currency pound = new Currency("British Pound", "United Kingdom", "GBP");
		Currency nzDollar = new Currency("New Zealand Dollar", "New Zealand", "NZD");
		Currency auDollar = new Currency("Austriallian Dollar", "Austrialia", "AUD");
		Currency yen = new Currency("Japanese Yen", "Japan", "JPY");
		Currency forint = new Currency("Hungarian Forint ", "Hungary", "HUF");
		List<Currency> currencies = Arrays.asList(euro, usDollar, pound, nzDollar, auDollar, yen, forint);
		logger.info("Creating %s currencies for exchange rate conversion", currencies.size());
		currencyService.save(currencies);
	}

	public void pullLatestExchangeRates() {
		String url = String.format("%s/%s?app_id=%s", basePath, latestExchangePath, appId);
		logger.trace("Calling %s", url);
		logger.info("Fetching last of ten currency exchange historyfrom openexchange...");
		ResponseEntity<ExchangeHistory> response = restTemplate.getForEntity(url, ExchangeHistory.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			ExchangeHistory exchangeHistory = response.getBody();
			List<Currency> currencies = currencyService.get();
			List<Exchange> exchanges = exchangeHistory.currencyExchanges(currencies);
			logger.info("Creating %s exchange rates for %s currencies", exchanges.size(), currencies.size());
			exchangeService.save(exchanges);
		} else {
			logger.error("Unable to get latest exchange rate at this time");
		}
	}

}
