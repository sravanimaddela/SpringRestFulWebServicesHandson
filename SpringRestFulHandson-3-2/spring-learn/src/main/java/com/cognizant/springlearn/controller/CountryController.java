package com.cognizant.springlearn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.exception.CountryNotFoundException;
import com.cognizant.springlearn.service.CountryService;

@RestController
public class CountryController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
	
	@Autowired
	CountryService countryService;
	
	@GetMapping("/country")
	public Country getCountryIndia() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("countries.xml");
		LOGGER.info("END");
		return context.getBean("in",Country.class);
	}
	
	@GetMapping("/countries")
	public List<Country> getAllCountries(){
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("countries.xml");
		LOGGER.info("END");
		return (List<Country>)context.getBean("countryList");
	}
	@GetMapping("/countries/{code}")
	public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
		return countryService.getCountry(code);
	}
	

}
