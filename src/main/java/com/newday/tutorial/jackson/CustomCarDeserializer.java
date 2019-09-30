package com.newday.tutorial.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomCarDeserializer extends StdDeserializer<Car> {
	public CustomCarDeserializer() {
		this(null);
	}

	public CustomCarDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Car deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Car car = new Car();
		ObjectCodec codec = parser.getCodec();
		JsonNode node = codec.readTree(parser);
		JsonNode colorNode = node.get("color");
		String color = colorNode.asText();
		car.setColor(color);
		return car;
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("CustomCarDeserializer", new Version(1, 0, 0, null, null, null));
		module.addDeserializer(Car.class, new CustomCarDeserializer());
		mapper.registerModule(module);
		Car car = mapper.readValue(json, Car.class);
		System.out.println(car);
	}

}
