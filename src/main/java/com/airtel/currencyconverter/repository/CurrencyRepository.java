
package com.airtel.currencyconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airtel.currencyconverter.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	public Currency findByCode(String code);
}
