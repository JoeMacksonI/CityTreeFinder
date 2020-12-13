package com.holidu.interview.assignment.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTestUtil {
	public JsonTestUtil() {
	}

	public static <T> T fromJson(String response, Class<T> classz) {
		try {
			return getObjectMapper().readValue(response, classz);
		} catch (Exception e) {
			throw new RuntimeException("Json conversion failed, test error", e);
		}
	}

	public static String toJson(Object toSerialize) {
		try {
			return getObjectMapper().writeValueAsString(toSerialize);
		} catch (Exception e) {
			throw new RuntimeException("Json conversion failed, test error", e);

		}
	}

	private static ObjectMapper getObjectMapper() {
		return ObjectMapperFactory.jsonObjectMapper();
	}
}
