package playground.response;

public class Response {

	private String message;
	
	private int httpStatusCode;
	
	public Response(String message, int httpStatusCode) {
		this.message = message;
		this.httpStatusCode = httpStatusCode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(Integer httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

}
