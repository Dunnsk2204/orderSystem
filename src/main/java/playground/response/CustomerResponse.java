package playground.response;

public class CustomerResponse extends Response {
	
	
	public CustomerResponse(String message, int httpStatusCode) {
		super(message, httpStatusCode);
	}

	public CustomerResponse(String message, int httpStatusCode, String customerName, String streetAddress, String city,
			String postcode, String country, String contactName) {
		super(message, httpStatusCode);
		this.customerName = customerName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.postcode = postcode;
		this.country = country;
		this.contactName = contactName;
	}

	private String customerName;
	
	private String streetAddress;
	
	private String city;
	
	private String postcode;
	
	private String country;
	
	private String contactName;


	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

}
