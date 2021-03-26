package com.airtel.currencyconverter.controller.form;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.airtel.currencyconverter.model.Conversion;
import com.airtel.currencyconverter.model.Currency;

public class QueryForm {

	@NotNull(message = "No date supplied")
	@NotEmpty(message = "Select a date")
	public String date;
	@NotNull(message = "No currency Supplied")
	@NotEmpty(message = "Select a date")
	public String currency;
	@NotNull(message = "No amount Supplied")
	@Min(value = 1, message = "Enter an amount greater than 0")
	public Float amount = 0F;
	public Float result = null;
	public List<String> queryHistory;
	public List<Currency> currencies;
	public List<Conversion> conversions;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getResult() {
		return result;
	}

	public void setResult(Float result) {
		this.result = result;
	}

	public List<String> getQueryHistory() {
		return this.queryHistory;
	}

	public void setQueryHistory(List<String> queryHistory) {
		this.queryHistory = queryHistory;
	}

	public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}

	public List<Conversion> getConversions() {
		return conversions;
	}

	public void setConversions(List<Conversion> conversions) {
		this.conversions = conversions;
	}

}
