package cl.api.processrequests.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class ToolUtil {

	private ToolUtil() {

	}

	public static <T> T fromJson(byte[] json, Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(json, tClass);
		} catch (IOException e) {
			return null;
		}
	}

	public static Map fromJson(String json) {
		try {
			return new ObjectMapper().readValue(json, HashMap.class);
		} catch (IOException e) {
			return null;
		}
	}

	public static String toJson(Object json) {
		try {
			return new ObjectMapper().writeValueAsString(json);
		} catch (IOException e) {
			return null;
		}
	}

}
