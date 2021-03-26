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
import com.airtel.currencyconverter.util.CurrencyUtil;

@Service
public class OpenExchangeApiService {

	private static Logger logger = LoggerFactory.getLogger(OpenExchangeApiService.class);

	@Value("${openexchange.appid}")
	private String appId;

	@Value("${openexchange.apipath.base}")
	private String basePath;

	@Value("${openexchange.apipath.latest}")
	private String latestExchangePath;

	@Value("${openexchange.apipath.historical}")
	private String historicalExchangePath;

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
		pullDateExchangeRates("latest");
	}

	public void createCurrencies() {
		if(currencyService.get().isEmpty()) {
			Currency euro = new Currency("Euro", "Europe", "EUR");
			Currency usDollar = new Currency("US Dollar", "United States", CurrencyUtil.DOLLAR);
			Currency pound = new Currency("British Pound", "United Kingdom", "GBP");
			Currency nzDollar = new Currency("New Zealand Dollar", "New Zealand", "NZD");
			Currency auDollar = new Currency("Austriallian Dollar", "Austrialia", "AUD");
			Currency yen = new Currency("Japanese Yen", "Japan", "JPY");
			Currency forint = new Currency("Hungarian Forint ", "Hungary", "HUF");
			Currency naira = new Currency("Naira ", "Nigeria", "NGN");
			List<Currency> currencies = Arrays.asList(euro, usDollar, pound, nzDollar, auDollar, yen, forint, naira);
			logger.info("Creating %s currencies for exchange rate conversion", currencies.size());
			currencyService.save(currencies);
		}
	}

	public void pullDateExchangeRates(String date) {
		String url = date == null || date.equals("latest")
			? String.format("%s/%s?app_id=%s", basePath, latestExchangePath, appId)
			: String.format("%s/%s/%s.json?app_id=%s", basePath, historicalExchangePath, date, appId);
		logger.trace("Calling %s", url);
		logger.info("Fetching last of ten currency exchange history from openexchange api service...");
		try {
			ResponseEntity<ExchangeHistory> response = restTemplate.getForEntity(url, ExchangeHistory.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				ExchangeHistory exchangeHistory = response.getBody();
				List<Currency> currencies = currencyService.get();
				List<Exchange> exchanges = exchangeHistory.currencyExchanges(currencies);
				for (Exchange exchange : exchanges) {
					exchange.setExchangeDate(date);
				}
				logger.info("Creating %s exchange rates for %s currencies", exchanges.size(), currencies.size());
				exchangeService.save(exchanges);
			} else {
				logger.error("Unable to get latest exchange rate at this time");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error(exception.getMessage());
		}
	}

}
