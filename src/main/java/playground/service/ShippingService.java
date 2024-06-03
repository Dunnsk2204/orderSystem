package playground.service;

import java.util.List;

import playground.entity.Shipping;
import playground.requests.ShippingRequest;
import playground.response.ShippingResponse;

public interface ShippingService {
	
	List<Shipping> getAllShippingDetails();
	
	Shipping getShippingDetailsById(String id);
	
	ShippingResponse saveNewShippingDetails(ShippingRequest shipping);

}
