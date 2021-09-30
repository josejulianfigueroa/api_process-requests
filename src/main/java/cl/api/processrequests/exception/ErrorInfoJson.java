package cl.api.processrequests.exception;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "message", "field" })
public class ErrorInfoJson {

	@JsonProperty("message")
	private String message;
	@JsonProperty("field")
	private String field;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	public ErrorInfoJson(String message, String field) {
		super();
		this.message = message;
		this.field = field;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("field")
	public String getField() {
		return field;
	}

	@JsonProperty("field")
	public void setField(String field) {
		this.field = field;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
