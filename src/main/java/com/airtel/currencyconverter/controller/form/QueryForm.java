package com.airtel.currencyconverter.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class QueryForm {

	@NotNull
	@NotEmpty
	public String date;
	public List<String> queryHistory;
	public String email;

	public String getDate() {
		return date;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<String> getQueryHistory() {
		return this.queryHistory;
	}

	public void setQueryHistory(List<String> queryHistory) {
		this.queryHistory = queryHistory;
	}

}
