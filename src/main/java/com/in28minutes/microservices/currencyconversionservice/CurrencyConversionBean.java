package com.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;

public class CurrencyConversionBean {

	private Long id;
	private String from;
	private String to;
	private BigDecimal multiploConversion;
	private BigDecimal quantity;
	private BigDecimal totalMontoCalculado;
	private int puerto;
	
	public CurrencyConversionBean() {
		
	}
	
	public CurrencyConversionBean(Long id, String from, String to, BigDecimal multiploConversion, BigDecimal quantity,
			BigDecimal totalMontoCalculado, int puerto) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.multiploConversion = multiploConversion;
		this.quantity = quantity;
		this.totalMontoCalculado = totalMontoCalculado;
		this.puerto = puerto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getMultiploConversion() {
		return multiploConversion;
	}

	public void setMultiploConversion(BigDecimal multiploConversion) {
		this.multiploConversion = multiploConversion;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalMontoCalculado() {
		return totalMontoCalculado;
	}

	public void setTotalMontoCalculado(BigDecimal totalMontoCalculado) {
		this.totalMontoCalculado = totalMontoCalculado;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	
}
