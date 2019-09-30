package com.newday.tutorial.jackson;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class TestCaseJacksonAnnotation {

	public TestCaseJacksonAnnotation() {
	}

	// @Test
	public void whenSerializingUsingJsonGetter_thenCorrect() throws JsonProcessingException {
		MyBean bean = new MyBean(1, "My Xbean");
		String result = new ObjectMapper().writeValueAsString(bean);
		System.out.println("对象的JSON表示：\n" + result);
		assertThat(result, containsString("My bean"));
		assertThat(result, containsString("1"));
	}

	// @Test
	public void whenSerializingUsingJsonRawValue_thenCorrect() throws JsonProcessingException {
		RawBean bean = new RawBean("My Xbean", "{\"attr\":false}");
		String result = new ObjectMapper().writeValueAsString(bean);
		System.out.println("RawBean对象的JSON表示：\n" + result);
		assertThat(result, containsString("My Xbean"));
		assertThat(result, containsString("{\"attr\":false}"));
	}

	// @Test
	public void serializingUsingJsonValue() throws JsonParseException, IOException {
		String enumAsString = new ObjectMapper().writeValueAsString(TypeEnumWithValue.TYPE1);
		System.out.println("序列化枚举对象name值：\n" + enumAsString);
		assertThat(enumAsString, is("\"Type A\""));
	}

	// @Test
	public void serializingUsingJsonRootName() throws JsonProcessingException {
		UserWithRoot user = new UserWithRoot(18, "Solo");
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		String result = mapper.writeValueAsString(user);
		System.out.println("包装后JSON对象：\n" + result);
		assertThat(result, containsString("Solo"));
		assertThat(result, containsString("user"));
		XmlMapper xm = new XmlMapper();
		String sxml = xm.writeValueAsString(user);
		System.out.println("XML化JSON对象：\n" + sxml);
	}

	// @Test
	public void serializingUsingJsonSerialize() throws JsonProcessingException, ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String toParse = "2019-10-01 08:30:00";
		Date date = df.parse(toParse);
		Event event = new Event();
		event.name = "party";
		event.eventDate = date;
		String result = new ObjectMapper().writeValueAsString(event);
		System.out.println("自定义序列化器：\n" + result);
		assertThat(result, containsString(toParse));
	}

	// @Test
	public void deserializingUsingJsonCreator() throws IOException {
		String json = "{\"id\":18,\"theName\":\"My xbean\"}";
		System.out.println("原JSON对象：\n" + json);
		BeanWithCreator bean = new ObjectMapper().readerFor(BeanWithCreator.class).readValue(json);
		assertEquals("My xbean", bean.name);
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(bean);
		System.out.println("反序列化对象再序列化：\n" + result);
	}

	// @Test
	public void deserializingUsingJsonInject() throws IOException {
		// String json = "{\"name\":\"My Xbean\"}";
		String json = "{\"name\":\"My Xbean\",\"type\":\"bean\"}";// json不能提供type
		InjectableValues inject = new InjectableValues.Std().addValue(int.class, 18).addValue(String.class, "xBean");// 可创建多个待注入值
		BeanWithInject bean = new ObjectMapper().reader(inject).forType(BeanWithInject.class).readValue(json);
		assertEquals("My Xbean", bean.name);
		assertEquals(18, bean.id);
		// assertEquals("xBean", bean.type);
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(bean);
		System.out.println("反序列化对象再序列化：\n" + result);
	}

	// @Test
	public void deserializingUsingJsonAnySetter() throws IOException {
		String json = "{\"name\":\"My Xbean\",\"attr2\":\"val2\",\"attr1\":\"val1\"}";
		ExtendableXBean bean = new ObjectMapper().readerFor(ExtendableXBean.class).readValue(json);
		assertEquals("My Xbean", bean.name);
		assertEquals("val2", bean.getProperties().get("attr2"));
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(bean);
		System.out.println("反序列化对象再序列化：\n" + result);
	}

	// @Test
	public void deserializingUsingJsonSetter() throws IOException {
		String json = "{\"id\":18,\"name\":\"My bean\"}";
		MyBean bean = new ObjectMapper().readerFor(MyBean.class).readValue(json);
		assertEquals("My bean", bean.getTheName());
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(bean);
		System.out.println("反序列化对象再JSON化：\n" + result);
	}

	// 有问题？
	// @Test
	public void deserializingUsingJsonDeserialize() throws IOException {
		String json = "{\"name\":\"xparty\",\"eventDate\":\"27-09-2019 02:30:00\"}";
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Event event = new ObjectMapper().readerFor(Event.class).readValue(json);
		// System.out.println(event.eventDate.toLocaleString());
		assertEquals("27-09-2019", df.format(event.eventDate));
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(event);
		System.out.println("反序列化对象再JSON化：\n" + result);
	}

	// @Test
	public void whenDeserializingUsingJsonAlias_thenCorrect() throws IOException {
		String json = "{\"fName\": \"Solo\", \"lastName\":	\"Cui\"}";
		AliasBean aliasBean = new ObjectMapper().readerFor(AliasBean.class).readValue(json);
		assertEquals("Solo", aliasBean.getFirstName());
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(aliasBean);
		System.out.println("反序列化对象再JSON化：\n" + result);
	}

	// @Test
	public void deserializingUsingCustomJsonDeserialize() throws IOException {
		String json = "{\"name\":\"party\",\"eventDate\":\"2018-12-20 02:30:00\"}";
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		MyEvent event = new ObjectMapper().readerFor(MyEvent.class).readValue(json);
		assertEquals("20-12-2018 02:30:00", df.format(event.eventDate));
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(event);
		System.out.println("反序列化对象再JSON化：\n" + result);
	}

	// @Test
	public void serializingPolymorphic() throws JsonProcessingException {
		Zoo.Dog dog = new Zoo.Dog("facy");
		Zoo zoo = new Zoo(dog);
		String result = new ObjectMapper().writeValueAsString(zoo);
		assertThat(result, containsString("type"));
		assertThat(result, containsString("dog"));
		System.out.println("序列化：\n" + result);
	}

	// @Test
	public void deserializingPolymorphicZoo() throws IOException {
		String json = "{\"animal\":{\"name\":\"Cacy\",\"type\":\"cat\"}}";
		Zoo zoo = new ObjectMapper().readerFor(Zoo.class).readValue(json);
		assertEquals("Cacy", zoo.animal.name);
		assertEquals(Zoo.Cat.class, zoo.animal.getClass());
		// zoo.animal.name = "Lucy";//
		String result = new ObjectMapper().writeValueAsString(zoo);

		System.out.println("再序列化：\n" + result);
	}

	// @Test
	public void usingJsonPropertyCase() throws IOException {
		EntityBean bean = new EntityBean(11, "My EntityBean");
		String result = new ObjectMapper().writeValueAsString(bean);
		System.out.println("实体JSON化：\n" + result);
		assertThat(result, containsString("My EntityBean"));
		assertThat(result, containsString("11"));
		EntityBean resultBean = new ObjectMapper().readerFor(EntityBean.class).readValue(result);
		assertEquals("My EntityBean", resultBean.getTheName());
	}

	// @Test
	public void serializingUsingJsonFormatCase() throws JsonProcessingException, ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		String toParse = "2019-09-27 08:30:00";
		Date date = df.parse(toParse);
		EventBean event = new EventBean("Have a party", date);
		String result = new ObjectMapper().writeValueAsString(event);
		assertThat(result, containsString(toParse));
		System.out.println("EventBean序列化：\n" + result);
	}

	// @Test
	public void serializingUsingJsonUnwrappedCase() throws JsonProcessingException, ParseException {
		UnwrappedUser.Name name = new UnwrappedUser.Name("Solo", "Cui");
		UnwrappedUser user = new UnwrappedUser(11, name);
		String result = new ObjectMapper().writeValueAsString(user);
		assertThat(result, containsString("Solo"));
		assertThat(result, not(containsString("name")));
		System.out.println("展开/解包JSON数据：\n" + result);
	}

	// @Test
	public void serializingUsingJsonViewCase() throws JsonProcessingException {
		Itemx item = new Itemx(22, "book", "Solo");
		String result = new ObjectMapper().writerWithView(Views.Public.class).writeValueAsString(item);
		assertThat(result, containsString("book"));
		assertThat(result, containsString("22"));
		assertThat(result, not(containsString("Solo")));
		System.out.println("指定视图化注解的JSON数据：\n" + result);
		String result2 = new ObjectMapper().writerWithView(Views.Internal.class).writeValueAsString(item);
		System.out.println("所有视图化注解JSON数据：\n" + result2);
	}

	// @Test
	public void serializingUsingJacksonReferenceAnnotationCase() throws JsonProcessingException {
		UserWithRef user = new UserWithRef(11, "Solo");
		ItemWithRef item = new ItemWithRef(22, "book", user);
		user.addItem(item);
		String result = new ObjectMapper().writeValueAsString(item);
		assertThat(result, containsString("book"));
		assertThat(result, containsString("Solo"));
		assertThat(result, not(containsString("userItems")));
		System.out.println("循环对象引用的JSON数据(ItemWithRef)：\n" + result);
		String result2 = new ObjectMapper().writeValueAsString(user);
		System.out.println("循环对象引用的JSON数据(UserWithRef)：\n" + result2);
	}

	// @Test
	public void serializingUsingJsonIdentityInfoCase() throws JsonProcessingException {
		UserWithIdentity user = new UserWithIdentity(11, "Solo");
		ItemWithIdentity item = new ItemWithIdentity(28, "book", user);
		user.addItem(item);
		String result = new ObjectMapper().writeValueAsString(item);
		assertThat(result, containsString("book"));
		assertThat(result, containsString("Solo"));
		assertThat(result, containsString("userItems"));
		System.out.println("双向引用的JSON数据(ItemWithIdentity)：\n" + result);
		String result2 = new ObjectMapper().writeValueAsString(user);
		System.out.println("双向引用的JSON数据(UserWithIdentity)：\n" + result2);
	}

	// @Test
	public void serializingUsingJsonFilterCase() throws JsonProcessingException {
		BeanWithFilter bean = new BeanWithFilter(11, "My xbean", "Dog");
		FilterProvider filters0 = new SimpleFilterProvider().addFilter("myFilter",
				SimpleBeanPropertyFilter.serializeAll());
		String result0 = new ObjectMapper().writer(filters0).writeValueAsString(bean);
		System.out.println("未过滤JSON数据（所有属性）：\n" + result0);
		FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter",
				SimpleBeanPropertyFilter.filterOutAllExcept("name", "type"));
		String result = new ObjectMapper().writer(filters).writeValueAsString(bean);
		assertThat(result, containsString("My xbean"));
		assertThat(result, not(containsString("id")));
		System.out.println("过滤后JSON数据：\n" + result);

	}

	// @Test
	public void serializingUsingCustomAnnotationCase() throws JsonProcessingException {
		BeanWithCustomAnnotation bean = new BeanWithCustomAnnotation(11, "My xbean", new Date());
		String result = new ObjectMapper().writeValueAsString(bean);
		assertThat(result, containsString("My xbean"));
		assertThat(result, containsString("11"));
		assertThat(result, containsString("xdateCreated"));
		// assertThat(result, not(containsString("xdateCreated")));//构造器第三参数为null时可启用
		System.out.println("应用自定义注释生成的JSON数据：\n" + result);
	}

	// @Test//需要再测试一个混入注释
	public void serializingUsingMixInAnnotationCase() throws JsonProcessingException, ParseException {
		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); String
		 * toParse = "2019-09-27 08:30:00"; Date date = df.parse(toParse);
		 */
		XItem item = new XItem(11, "book", new User(), new Date());
		String result = new ObjectMapper().writeValueAsString(item);
		assertThat(result, containsString("owner"));
		ObjectMapper mapper = new ObjectMapper();
		result = mapper.writeValueAsString(item);
		assertThat(result, containsString("owner"));
		System.out.println("未用混合注释时JSON数据：\n" + result);

		ObjectMapper mapper1 = new ObjectMapper();
		mapper1.addMixIn(User.class, MyMixInForIgnoreType.class);
		result = mapper1.writeValueAsString(item);
		assertThat(result, not(containsString("owner")));
		System.out.println("使用混合注释后JSON数据：\n" + result);
	}

	// @Test
	public void whenDisablingAllAnnotations_thenAllDisabled() throws IOException {
		MyXBean bean = new MyXBean(11, null);
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(bean);
		System.out.println("禁用注释前JSON数据：\n" + result);// null值不包含

		ObjectMapper mapper1 = new ObjectMapper();
		mapper1.disable(MapperFeature.USE_ANNOTATIONS);// null值包含
		result = mapper1.writeValueAsString(bean);
		assertThat(result, containsString("11"));
		assertThat(result, containsString("name"));
		System.out.println("禁用注释后JSON数据：\n" + result);// 包含null值
	}

	// @Test
	public void givenFieldIsIgnoredByNameCase() throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		MyTask taskObject = new MyTask();
		taskObject.setBooleanValue(true);
		taskObject.setIntValue(100);
		taskObject.setStringValue("Work Hard:Writing~");
		String tskAsString = mapper.writeValueAsString(taskObject);
		assertThat(tskAsString, not(containsString("intValue")));
		System.out.println("忽略指定字段：\n" + tskAsString);
	}

	// @Test
	public final void givenFieldTypeIsIgnoredTaskIsSerializedCaset() throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(String[].class, XMixInForIgnoreType.class);
		MyTask mto = new MyTask();
		mto.setBooleanValue(true);
		String[] sa = { "aaa", "bbb" };
		mto.setStrValues(sa);
		mto.setStringValue("Work:Writing");
		String dtoAsString = mapper.writeValueAsString(mto);
		assertThat(dtoAsString, not(containsString("intValue")));
		assertThat(dtoAsString, not(containsString("booleanValue")));
		assertThat(dtoAsString, not(containsString("strValues")));
		assertThat(dtoAsString, containsString("stringValue"));
		System.out.println("忽略指定字段：\n" + dtoAsString);
	}

	// @Test
	public void givenFilterIgnoresFieldByNamMyTaskIsCase() throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept("middleValue");
		FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", theFilter);
		MyTask mtObject = new MyTask();
		mtObject.setBooleanValue(true);
		String[] sa = { "aaa", "bbb" };
		mtObject.setStrValues(sa);
		mtObject.setMiddleValue("Middle-value");
		mtObject.setStringValue("Work:Writing");
		String dtoAsString = mapper.writer(filters).writeValueAsString(mtObject);
		assertThat(dtoAsString, not(containsString("middleValue")));
		assertThat(dtoAsString, containsString("stringValue"));
		System.out.println(dtoAsString);
	}

	// @Test
	public void ignoredOnClassObjectWithNullFieldCase() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		MySomeThing mtObject = new MySomeThing();
		mtObject.setLastValue("Work:Writing");
		String dtoAsString = mapper.writeValueAsString(mtObject);
		assertThat(dtoAsString, containsString("lastValue"));
		assertThat(dtoAsString, not(containsString("initValue")));
		System.out.println(dtoAsString);
	}

	// @Test
	public void allNullsIgnoredGloballyWithNullFieldCase() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		MySomeThing mtObject = new MySomeThing();
		mtObject.setLastValue("Work:Writing");
		mtObject.setIntValue(12);
		mtObject.setBoolValue(true);
		String dtoAsString = mapper.writeValueAsString(mtObject);
		assertThat(dtoAsString, containsString("intValue"));
		assertThat(dtoAsString, containsString("boolValue"));
		assertThat(dtoAsString, not(containsString("initValue")));
		System.out.println(dtoAsString);
	}

	// @Test
	public void givenNameOfFieldIsChangedCase() throws JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		MyEntity meObject = new MyEntity();
		meObject.setStringValue("a value");
		String meAsString = mapper.writeValueAsString(meObject);
		assertThat(meAsString, not(containsString("stringValue")));
		assertThat(meAsString, containsString("strVal"));
		System.out.println(meAsString);
	}

	// @Test
	public void jsonHasUnknownValuesDeserializinException()
			throws JsonParseException, JsonMappingException, IOException {
		String jsonAsString = "{\"stringValue\":\"a\"," + "\"intValue\":1," + "\"booleanValue\":true,"
				+ "\"stringValue2\":\"something\"}";
		ObjectMapper mapper = new ObjectMapper();
		MyEntity readValue = mapper.readValue(jsonAsString, MyEntity.class);
		System.out.println(readValue);
		assertNotNull(readValue);
	}

	// @Test
	public void ignoringUnknownsWhenDeserializingCase() throws JsonParseException, JsonMappingException, IOException {
		String jsonAsString = "{\"stringValue\":\"avalue\"," + "\"intValue\":11," + "\"booleanValue\":true,"
				+ "\"stringValue2\":\"something\"}";
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MyEntity readValue = mapper.readValue(jsonAsString, MyEntity.class);
		assertNotNull(readValue);
		assertThat(readValue.getStringValue(), equalTo("avalue"));
		assertThat(readValue.isBooleanValue(), equalTo(true));
		assertThat(readValue.getIntValue(), equalTo(11));
		System.out.println(readValue);
	}

	//@Test
	public void ignoringUnknownsDeserializingOnClassCase()
			throws JsonParseException, JsonMappingException, IOException {
		String jsonAsString = "{\"stringValue\":\"avalue\"," + "\"intValue\":18," + "\"booleanValue\":true,"
				+ "\"stringValue2\":\"something\"}";
		ObjectMapper mapper = new ObjectMapper();
		MyEntity readValue = mapper.readValue(jsonAsString, MyEntity.class);
		assertNotNull(readValue);
		assertThat(readValue.getStringValue(), equalTo("avalue"));
		assertThat(readValue.isBooleanValue(), equalTo(true));
		assertThat(readValue.getIntValue(), equalTo(18));
		System.out.println(readValue);
	}

	@Test
	public void notAllHaveValuesInJsonDeserializingAJsonToAClass()
			throws JsonParseException, JsonMappingException, IOException {
		String jsonAsString = "{\"booleanValue\":true}";
		ObjectMapper mapper = new ObjectMapper();
		MyEntity readValue = mapper.readValue(jsonAsString, MyEntity.class);
		assertNotNull(readValue);
		//assertThat(readValue.getStringValue(), equalTo("avalue"));
		assertThat(readValue.isBooleanValue(), equalTo(true));
		System.out.println(readValue);
	}

}
