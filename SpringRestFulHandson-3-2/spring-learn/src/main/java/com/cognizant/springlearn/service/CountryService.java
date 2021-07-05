package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.controller.CountryController;
import com.cognizant.springlearn.exception.CountryNotFoundException;

@Service
public class CountryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
	public Country getCountry(String code) throws CountryNotFoundException {
		LOGGER.info("START");
		ApplicationContext context = new ClassPathXmlApplicationContext("countries.xml");
		List<Country> countryList = (List<Country>)context.getBean("countryList");
		Country country = null;
		for(Country c:countryList)
			if(c.getCode().equalsIgnoreCase(code))
			{
				country = c;
			}
		if(country==null) 
			throw new CountryNotFoundException();
		LOGGER.info("END");
		return country;
		
	}

}
