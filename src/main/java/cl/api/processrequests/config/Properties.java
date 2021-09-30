package cl.api.processrequests.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "myapp")
public class Properties {

	private final Map<String, String> myProperties = new HashMap<>();

	public Map<String, String> getProperties() {
		return myProperties;
	}

}
