package com.airtel.currencyconverter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = CurrencyConverterApplication.class)
@AutoConfigureTestEntityManager
class CurrencyConverterApplicationTests {

	@Test
	void contextLoads() {
	}

}
