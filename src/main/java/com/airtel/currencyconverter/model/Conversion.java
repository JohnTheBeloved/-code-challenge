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
@Table(name = "conversion")
public class Conversion {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "currency_from_id", referencedColumnName = "id")
	private Currency currencyFrom;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "currency_to_id", referencedColumnName = "id")
	private Currency currencyTo;
	private Float amountFrom;
	private Float amountTo;

	public Conversion() {
	}

	public Conversion(Currency currencyFrom, Currency currencyTo, Float amountFrom) {
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.amountFrom = amountFrom;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Currency getCurrencyFrom() {
		return this.currencyFrom;
	}

	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public Currency getCurrencyTo() {
		return this.currencyTo;
	}

	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}

	@Column(name = "amount_from", nullable = false)
	public Float getAmountFrom() {
		return this.amountFrom;
	}

	public void setAmountFrom(Float amountFrom) {
		this.amountFrom = amountFrom;
	}

	@Column(name = "amount_from", nullable = false)
	public Float getAmountTo() {
		return this.amountTo;
	}

	public void setAmountTo(Float amountTo) {
		this.amountTo = amountTo;
	}

}
