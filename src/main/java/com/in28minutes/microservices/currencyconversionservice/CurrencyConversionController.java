package com.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
//	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
//	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
//		return new CurrencyConversionBean(1L, from, to, BigDecimal.ONE, quantity, quantity, 0);
//	}
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		/**
		 * Los parámetros que se pasan son:
		 * Primer parámetro: URI del servicio de la aplicacion currency-exchange-service necesitamos consumir.
		 * Segundo parámetro: La respuesta se debe devolver en un objeto de tipo CurrencyConversionBean.class.
		 * Tercer parámetro: Se pasan los parámetros que requiere el servicio que estamos invocando.
		 * 
		 * IMPORTANTE: 
		 * Invocamos el servicio http://localhost:8000/currency-exchange/from/{from}/to/{to} que devuelve un objeto de tipo ExchangeValue.java
		 * pero le indicamos al RestTemplate que la respuesta la mepee a un objeto de tipo CurrencyConversionBean.java
		 * por lo que es imortante que los campos en comun se llemen igual en ambos beans (id, from, to, multiploConversion) de no ser así no se mapearan correctamente los valores. 
		 * 
		 */ 
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
		
		//Obtenemos la respuesta del servicio.
		CurrencyConversionBean response	= responseEntity.getBody();
		
		//Obtenemos la información que necesitamos.
		return new CurrencyConversionBean(response.getId(), from, to, response.getMultiploConversion(), quantity, quantity.multiply(response.getMultiploConversion()), response.getPuerto());
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		
		//A través del proxy consumimos el servicio de la aplicación currency-exchange-service.
		CurrencyConversionBean response	= proxy.retrieveValue(from, to);
		
		logger.info("*************** Message From CurrencyConversionController ---->>> {}", response);
		logger.info("{}", response);
		
		//Obtenemos la información que necesitamos.
		return new CurrencyConversionBean(response.getId(), from, to, response.getMultiploConversion(), quantity, quantity.multiply(response.getMultiploConversion()), response.getPuerto());
	}
	
}
