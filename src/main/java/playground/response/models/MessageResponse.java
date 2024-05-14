package playground.response.models;

public class MessageResponse {

	private String message;
	private Integer httpStatus;

	public MessageResponse(String message, int httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httStatus) {
		this.httpStatus = httStatus;
	}

}
