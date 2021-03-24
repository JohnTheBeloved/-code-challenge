package com.airtel.currencyconverter.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "exchange")
public class Exchange {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "currency_id", referencedColumnName = "id")
	private Currency currency;
	private String exchangeDate;
	private Double rate;

	public Exchange() {

	}

	public Exchange(Currency currency, String exchangeDate, Double rate) {
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

	public void setDate(String exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

	@Column(name = "rate", nullable = false)
	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}
