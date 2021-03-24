
package com.airtel.currencyconverter.openexchange.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.airtel.currencyconverter.model.Currency;
import com.airtel.currencyconverter.model.Exchange;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"AUD",
	"EUR",
	"GBP",
	"HUF",
	"JPY",
	"NZD",
	"USD" })
public class Rates {

	@JsonProperty("AUD")
	private Double aUD;
	@JsonProperty("EUR")
	private Double eUR;
	@JsonProperty("GBP")
	private Double gBP;
	@JsonProperty("HUF")
	private Double hUF;
	@JsonProperty("JPY")
	private Double jPY;
	@JsonProperty("NZD")
	private Double nZD;
	@JsonProperty("USD")
	private Double uSD;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("AUD")
	public Double getAUD() {
		return aUD;
	}

	@JsonProperty("AUD")
	public void setAUD(Double aUD) {
		this.aUD = aUD;
	}

	@JsonProperty("EUR")
	public Double getEUR() {
		return eUR;
	}

	@JsonProperty("EUR")
	public void setEUR(Double eUR) {
		this.eUR = eUR;
	}

	@JsonProperty("GBP")
	public Double getGBP() {
		return gBP;
	}

	@JsonProperty("GBP")
	public void setGBP(Double gBP) {
		this.gBP = gBP;
	}

	@JsonProperty("HUF")
	public Double getHUF() {
		return hUF;
	}

	@JsonProperty("HUF")
	public void setHUF(Double hUF) {
		this.hUF = hUF;
	}

	@JsonProperty("JPY")
	public Double getJPY() {
		return jPY;
	}

	@JsonProperty("JPY")
	public void setJPY(Double jPY) {
		this.jPY = jPY;
	}

	@JsonProperty("NZD")
	public Double getNZD() {
		return nZD;
	}

	@JsonProperty("NZD")
	public void setNZD(Double nZD) {
		this.nZD = nZD;
	}

	@JsonProperty("USD")
	public Double getUSD() {
		return uSD;
	}

	@JsonProperty("USD")
	public void setUSD(Double uSD) {
		this.uSD = uSD;
	}

	//TODO: Test this
	public List<Exchange> toExchanges(List<Currency> currencies, Date date) {
		ArrayList<Exchange> exchanges = new ArrayList<>();
		for (Currency currency : currencies) {
			switch (currency.getCode()) {
			case "AUD":
				exchanges.add(new Exchange(currency, date, getAUD()));
				break;
			case "EUR":
				exchanges.add(new Exchange(currency, date, getEUR()));
				break;
			case "GBP":
				exchanges.add(new Exchange(currency, date, getGBP()));
				break;
			case "HUF":
				exchanges.add(new Exchange(currency, date, getHUF()));
				break;
			case "JPY":
				exchanges.add(new Exchange(currency, date, getJPY()));
				break;
			case "NZD":
				exchanges.add(new Exchange(currency, date, getNZD()));
				break;
			case "USD":
				exchanges.add(new Exchange(currency, date, getUSD()));
				break;
			default:

			}
		}
		return exchanges;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
