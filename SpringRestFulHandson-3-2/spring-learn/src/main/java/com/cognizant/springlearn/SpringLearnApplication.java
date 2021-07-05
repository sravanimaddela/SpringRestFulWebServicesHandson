package com.cognizant.springlearn;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Date;
import java.util.List;
@SpringBootApplication
public class SpringLearnApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);
	public void displayDate() {
		LOGGER.info("START");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("date-format.xml");
		SimpleDateFormat format = ctx.getBean("dateFormat",SimpleDateFormat.class);
		try {
			Date date = format.parse("31/12/2018");
			LOGGER.debug(date.toString());
		}
		catch(Exception e) {
			System.out.println(e);
		}
		LOGGER.info("END");
	}
	
	public void displayCountry() {
		LOGGER.info("START");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("countries.xml");
		Country country = ctx.getBean("country",Country.class);
		Country anotherCountry = ctx.getBean("country",Country.class);
		LOGGER.debug("Country : {}",country.toString());
		LOGGER.debug("AnotherCountry : {}",anotherCountry.toString());
		LOGGER.info("END");
	}
	
	public void displayCountries() {
		LOGGER.info("START");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("countries.xml");
		List<Country> list = (List<Country>) ctx.getBean("countryList");
		LOGGER.debug("CountryList : {}",list.toString());
		LOGGER.info("END");
	}
	public static void main(String[] args) {
		
		SpringApplication.run(SpringLearnApplication.class, args);
		SpringLearnApplication spa = new SpringLearnApplication();
		spa.displayDate();
		spa.displayCountry();
		spa.displayCountries();
	}
}