package playground.response;

public class ShippingResponse extends Response {
	
	public ShippingResponse(String message, int httpStatusCode) {
		super(message, httpStatusCode);
		// TODO Auto-generated constructor stub
	}

	public ShippingResponse(String message, int httpStatusCode, String shipperId, String shipperName,
			String shipperNumber) {
		super(message, httpStatusCode);
		this.shipperId = shipperId;
		this.shipperName = shipperName;
		this.shipperNumber = shipperNumber;
	}

	private String shipperId;
	
	private String shipperName;
	
	private String shipperNumber;

	public String getShipperId() {
		return shipperId;
	}

	public void setShipperId(String shipperId) {
		this.shipperId = shipperId;
	}

	public String getShipperName() {
		return shipperName;
	}

	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}

	public String getShipperNumber() {
		return shipperNumber;
	}

	public void setShipperNumber(String shipperNumber) {
		this.shipperNumber = shipperNumber;
	}

}
