
package com.airtel.currencyconverter.openexchange.model;

import java.util.ArrayList;
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
	"NGN",
	"USD" })
public class Rates {

	@JsonProperty("AUD")
	private Float aUD;
	@JsonProperty("EUR")
	private Float eUR;
	@JsonProperty("GBP")
	private Float gBP;
	@JsonProperty("HUF")
	private Float hUF;
	@JsonProperty("JPY")
	private Float jPY;
	@JsonProperty("NZD")
	private Float nZD;
	@JsonProperty("NGN")
	private Float nGN;
	@JsonProperty("USD")
	private Float uSD;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("AUD")
	public Float getAUD() {
		return aUD;
	}

	@JsonProperty("AUD")
	public void setAUD(Float aUD) {
		this.aUD = aUD;
	}

	@JsonProperty("EUR")
	public Float getEUR() {
		return eUR;
	}

	@JsonProperty("EUR")
	public void setEUR(Float eUR) {
		this.eUR = eUR;
	}

	@JsonProperty("GBP")
	public Float getGBP() {
		return gBP;
	}

	@JsonProperty("GBP")
	public void setGBP(Float gBP) {
		this.gBP = gBP;
	}


	@JsonProperty("NGN")
	public Float getNGN() {
		return nGN;
	}

	@JsonProperty("NGN")
	public void setNGN(Float nGN) {
		this.nGN = nGN;
	}


	@JsonProperty("HUF")
	public Float getHUF() {
		return hUF;
	}

	@JsonProperty("HUF")
	public void setHUF(Float hUF) {
		this.hUF = hUF;
	}

	@JsonProperty("JPY")
	public Float getJPY() {
		return jPY;
	}

	@JsonProperty("JPY")
	public void setJPY(Float jPY) {
		this.jPY = jPY;
	}

	@JsonProperty("NZD")
	public Float getNZD() {
		return nZD;
	}

	@JsonProperty("NZD")
	public void setNZD(Float nZD) {
		this.nZD = nZD;
	}

	@JsonProperty("USD")
	public Float getUSD() {
		return uSD;
	}

	@JsonProperty("USD")
	public void setUSD(Float uSD) {
		this.uSD = uSD;
	}

	//TODO: Test this
	public List<Exchange> toExchanges(List<Currency> currencies, String date) {
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
