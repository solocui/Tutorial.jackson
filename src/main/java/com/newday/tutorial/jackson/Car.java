package com.newday.tutorial.jackson;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class Car {

	private String color;
	private String type;

	public Car() {
	}

	public Car(String color, String type) {
		super();
		this.color = color;
		this.type = type;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Car [color=" + color + ", type=" + type + "]";
	}

	public static void main(String[] args) {
		ObjectMapper objectMapper = new ObjectMapper();
		//String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		String jsonString = "{ \"color\" : \"Black\", \"type\" : \"BMW\",\"year\":\"2018\" }";
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule("CustomCarSerializer", 
										new Version(1, 0, 0,null, null, null));
			module.addSerializer(Car.class, new CustomCarSerializer());
			mapper.registerModule(module);
			Car car = new Car("yellow", "renault");
			String carJson = mapper.writeValueAsString(car);
			System.out.println(carJson);
//			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			Car car = objectMapper.readValue(jsonString, Car.class);
//			JsonNode jsonNodeRoot = objectMapper.readTree(jsonString);
//			JsonNode jsonNodeYear = jsonNodeRoot.get("year");
//			String year = jsonNodeYear.asText();
//			System.out.println(year);
//			objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
//			objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, false);
//			//Car car = objectMapper.readValue(json, Car.class);
//			Car car = objectMapper.readValue(new File("D:\\tmp\\myjson\\car-redflag.json"),	Car.class);
//			System.out.println("Car Message:"+car.toString());
//			Car car2 = objectMapper.readValue(new URL("file:///D:\\tmp\\myjson\\car-redflag.json"),
//					Car.class);
//			System.out.println("Car Message:"+car2.toString());
//			String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
//			Map<String, Object> map	= objectMapper.readValue(json, new
//			TypeReference<Map<String,Object>>(){});
//			String jsonCarArray =
//					"[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" :\"Red\", \"type\" : \"FIAT\" }]";
//			List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
			/*
			 * String json = "{ \"color\" : \"Black\", \"type\" : \"FIAT\" }"; JsonNode
			 * jsonNode = objectMapper.readTree(json); String color =
			 * jsonNode.get("color").asText(); // Output: color -> Black
			 * 
			 */		
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * Car car = new Car("yellow", "Volvo"); try { objectMapper.writeValue(new
		 * File("D:\\tmp\\myjson\\car.json"), car); String json =
		 * objectMapper.writeValueAsString(car); //byte[] jsba =
		 * objectMapper.writeValueAsBytes(car); System.out.println(json); } catch
		 * (JsonGenerationException e) { e.printStackTrace(); } catch
		 * (JsonMappingException e) { e.printStackTrace(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
	}

}
