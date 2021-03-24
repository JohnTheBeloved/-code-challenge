package com.airtel.currencyconverter.openexchange.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.airtel.currencyconverter.model.Currency;
import com.airtel.currencyconverter.model.Exchange;
import com.airtel.currencyconverter.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"disclaimer",
	"license",
	"timestamp",
	"base",
	"rates" })
public class ExchangeHistory {

	@JsonProperty("disclaimer")
	private String disclaimer;
	@JsonProperty("license")
	private String license;
	@JsonProperty("timestamp")
	private Long timestamp;
	@JsonProperty("base")
	private String base;
	@JsonProperty("rates")
	private Rates rates;// TODO: use hashmap instead //private Map<String, Double> rates = new HashMap<>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("disclaimer")
	public String getDisclaimer() {
		return disclaimer;
	}

	@JsonProperty("disclaimer")
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	@JsonProperty("license")
	public String getLicense() {
		return license;
	}

	@JsonProperty("license")
	public void setLicense(String license) {
		this.license = license;
	}

	@JsonProperty("timestamp")
	public Long getTimestamp() {
		return timestamp;
	}

	@JsonProperty("timestamp")
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@JsonProperty("base")
	public String getBase() {
		return base;
	}

	@JsonProperty("base")
	public void setBase(String base) {
		this.base = base;
	}

	@JsonProperty("rates")
	public Rates getRates() {
		return rates;
	}

	@JsonProperty("rates")
	public void setRates(Rates rates) {
		this.rates = rates;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public List<Exchange> currencyExchanges(List<Currency> currencies) {
		return rates.toExchanges(currencies, DateUtils.format(new Date(getTimestamp())));
	}

}
