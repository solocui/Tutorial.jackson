package com.newday.tutorial.jackson;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "xname", "xid", "xdateCreated" })
public @interface CustomAnnotation {

}
