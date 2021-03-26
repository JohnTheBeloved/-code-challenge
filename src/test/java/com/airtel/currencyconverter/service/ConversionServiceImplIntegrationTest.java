package com.airtel.currencyconverter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import com.airtel.currencyconverter.CurrencyConverterApplication;
import com.airtel.currencyconverter.model.Conversion;
import com.airtel.currencyconverter.model.Exchange;

@SpringBootTest(classes = CurrencyConverterApplication.class)
@AutoConfigureTestEntityManager
public class ConversionServiceImplIntegrationTest {

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private ExchangeService exchangeService;

	@Test
	@Transactional
		public void givenCurrency_andAmounts_returnCorrectConversion() throws Exception {
		String currencyCode = "NZD";
		String date = "2020-02-02";
		Float amountFrom = 100.09F;
		Conversion conversion = conversionService.create(currencyCode, date, amountFrom);
		//Manually calculate
		Exchange exchange = exchangeService.getDateExchange(conversion.getCurrencyTo(), date);
		Float rate = (float) (exchange != null ? exchange.getRate() : 0);
		Float amountTo = amountFrom * rate;
		assertEquals(conversion.getAmountTo(), amountTo);
	}
}
