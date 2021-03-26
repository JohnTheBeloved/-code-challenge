package com.airtel.currencyconverter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.airtel.currencyconverter.CurrencyConverterApplication;
import com.airtel.currencyconverter.model.Conversion;
import com.airtel.currencyconverter.model.Currency;
import com.airtel.currencyconverter.model.Exchange;
import com.airtel.currencyconverter.service.impl.ConversionServiceImpl;
import com.airtel.currencyconverter.service.impl.ExchangeServiceImpl;

@SpringBootTest(classes = CurrencyConverterApplication.class)
@AutoConfigureTestEntityManager
public class ConversionServiceImplIntegrationTest {


	public static Currency defaultCurrencyFrom = new Currency("A", "A", "A");
	public static Currency defaultCurrencyTo = new Currency("B", "B", "B");
	public static String defaultDate = "2020-12-12";
	public static Float defaultRate = 12.56F;
	public static Float testAmount = 66F;

	@MockBean
	private ConversionService conversionService;

	@MockBean
	private ExchangeService exchangeService;

	@Before
	public void setUp() {
		Exchange exchange = new Exchange(defaultCurrencyTo, defaultDate, defaultRate);
		Mockito.when(exchangeService.getDateExchanges(defaultDate, testAmount))
		.thenReturn(Arrays.asList(exchange));
		
  }

//	@Test FIXME: Mock inner beans
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
