package cl.api.processrequests.exception;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "result", "status", "error" })
public class ErrorResponseJson {

	@JsonProperty("result")
	private Boolean result;
	@JsonProperty("status")
	private StatusInfoJson status;
	@JsonProperty("error")
	private ErrorInfoJson error;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	public ErrorResponseJson(ResponseException e) {
		super();
		this.result = e.isResult();
		this.status = new StatusInfoJson(e.getStatusResponseEnum().getStatusCode(),
				e.getStatusResponseEnum().toString().replace("_", " "));
		this.error = new ErrorInfoJson(e.getMessage(), e.getField());
	}

	public ErrorResponseJson(Exception e) {
		super();
		this.result = false;
		this.status = new StatusInfoJson(StatusResponseEnum.INTERNAL_SERVER_ERROR.getStatusCode(),
				StatusResponseEnum.INTERNAL_SERVER_ERROR.toString().replace("_", " "));
		this.error = new ErrorInfoJson(e.getMessage(), "");
	}
	
	public ErrorResponseJson(String message) {
		super();
		this.result = false;
		this.status = new StatusInfoJson(StatusResponseEnum.PRECONDITION_FAILED.getStatusCode(),
				StatusResponseEnum.PRECONDITION_FAILED.toString().replace("_", " "));
		this.error = new ErrorInfoJson(message, "");
	}

	@JsonProperty("result")
	public Boolean getResult() {
		return result;
	}

	@JsonProperty("result")
	public void setResult(Boolean result) {
		this.result = result;
	}

	@JsonProperty("status")
	public StatusInfoJson getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(StatusInfoJson status) {
		this.status = status;
	}

	@JsonProperty("error")
	public ErrorInfoJson getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(ErrorInfoJson error) {
		this.error = error;
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
