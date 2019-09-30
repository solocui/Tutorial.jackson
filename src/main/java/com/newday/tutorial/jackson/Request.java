package com.newday.tutorial.jackson;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {
	private Car car;
	private Date datePurchased;

	public Request() {
	}

	Request(Car car, Date datePurchased) {
		super();
		this.car = car;
		this.datePurchased = datePurchased;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Date getDatePurchased() {
		return datePurchased;
	}

	public void setDatePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}

	public static void main(String[] args) throws IOException {
		String jsonCarArray = "[{ \"color\" : \"Black\", \"type\" : \"BMW\"}, { \"color\" :\"Red\", \"type\" : \"FIAT\" }]";
		ObjectMapper objectMapper = new ObjectMapper();
		//objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);
		//Car[] cars = objectMapper.readValue(jsonCarArray, Car[].class);
		List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
		for (Car c : listCar)
			System.out.println(c);
		// print cars
//		ObjectMapper objectMapper = new ObjectMapper();
//		Car car  = new Car("yellow","Tesla");
//		Request request = new Request(car,new Date());
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
//		objectMapper.setDateFormat(df);
//		String carAsString = objectMapper.writeValueAsString(request);
//		System.out.println(carAsString);
	}

}
