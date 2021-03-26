package com.airtel.currencyconverter.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exchange")
public class Exchange {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "currency_id", referencedColumnName = "id")
	private Currency currency;
	private String exchangeDate;
	private Float rate;
	private Float result;

	public Exchange() {

	}

	public Exchange(Currency currency, String exchangeDate, Float rate) {
		this.currency = currency;
		this.exchangeDate = exchangeDate;
		this.rate = rate;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Column(name = "exchange_date", nullable = false)
	public String getExchangeDate() {
		return exchangeDate;
	}

	public void setExchangeDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	@Column(name = "rate", nullable = false)
	public Float getRate() {
		return this.rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public void setResult(Float result) {
		this.result = result;
	}

	public Float getResult() {
		return this.result;
	}

	public Float calculateResult(Float amount) {
		return rate * amount;
	}

}
