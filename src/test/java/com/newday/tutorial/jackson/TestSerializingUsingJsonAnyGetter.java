package com.newday.tutorial.jackson;



import org.junit.Test;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestSerializingUsingJsonAnyGetter {

	public TestSerializingUsingJsonAnyGetter() {
	}

	@Test
	public void whenSerializingUsingJsonAnyGetter_thenCorrect() throws JsonProcessingException {
		ExtendableBean bean = new ExtendableBean("My Xbean");
		bean.add("attr1", "val1");
		bean.add("attr2", "val2");
		String result = new ObjectMapper().writeValueAsString(bean);
		System.out.println(result);
		assertThat(result, containsString("attr1"));
		assertThat(result, containsString("val1"));
	}

}
