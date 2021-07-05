package com.cognizant.springlearn;

import java.text.SimpleDateFormat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

@SpringBootApplication
public class SpringLearnApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	public void displayDate() {
		LOGGER.info("START");
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
		SimpleDateFormat format = context.getBean("dateFormat", SimpleDateFormat.class);
		Date date;
		try {
			date = format.parse("31/12/2018");
			LOGGER.debug(date.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		LOGGER.info("END");
	}

	public void displayCountry() {
		LOGGER.info("START");
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("countries.xml");
		Country country = context.getBean("country", Country.class);
		Country anotherCountry = context.getBean("country", Country.class);
		LOGGER.debug("Country : {}", country.toString());
		LOGGER.debug("AnotherCountry : {}", anotherCountry.toString());
		LOGGER.info("END");
	}

	public void displayCountries() {
		LOGGER.info("START");
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("countries.xml");
		@SuppressWarnings("unchecked")
		List<Country> list = (List<Country>) context.getBean("countryList");
		LOGGER.debug("CountryList : {}", list.toString());
		LOGGER.info("END");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringLearnApplication.class, args);
		SpringLearnApplication sla = new SpringLearnApplication();
		sla.displayDate();
		sla.displayCountry();
		sla.displayCountries();
	}

}
