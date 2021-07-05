package com.cognizant.springlearn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;
import com.cognizant.springlearn.exception.CountryNotFoundException;

@RestController
@RequestMapping("/countries")
public class CountryController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
	@Autowired
	CountryService countryService;

	@RequestMapping("/IN")
	public Country getCountryIndia() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("countries.xml");
		LOGGER.info("END");
		return context.getBean("country", Country.class);
	}

	@GetMapping("/")
	public List<Country> getAllCountries() {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("countries.xml");
		LOGGER.info("END");
		return (List<Country>) context.getBean("countryList");
	}

	@GetMapping("/{code}")
	public Country getCountry(@PathVariable String code) throws CountryNotFoundException {
		return countryService.getCountry(code);
	}

	@PostMapping
	public Country addCountry(@RequestBody Country country) {
		LOGGER.info("START");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Country>> violations = validator.validate(country);
		
		List<String> errors = new ArrayList<String>();
		for(ConstraintViolation<Country> violation : violations)
			errors.add(violation.getMessage());
		if(violations.size() > 0)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,errors.toString());
		LOGGER.debug("Country:{}",country);
		return country;
	}
}