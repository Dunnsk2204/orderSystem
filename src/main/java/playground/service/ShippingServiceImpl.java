package playground.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import playground.entity.Shipping;
import playground.repository.ShippingRepository;
import playground.requests.ShippingRequest;
import playground.response.ShippingResponse;

@Service
public class ShippingServiceImpl implements ShippingService {
	
	private final ShippingRepository shippingRepository;
	
	public ShippingServiceImpl(ShippingRepository shippingRepository) {
		this.shippingRepository = shippingRepository;
	}

	@Override
	public List<Shipping> getAllShippingDetails() {
		List<Shipping> shippingList = shippingRepository.findAll();
		return shippingList;
	}

	@Override
	public Shipping getShippingDetailsById(String id) {
		Optional<Shipping> shipping = shippingRepository.findById(id);
		
		if (!shipping.isEmpty()) {
			return shipping.get();
		}
		
		return new Shipping();
	}

	@Override
	public ShippingResponse saveNewShippingDetails(ShippingRequest shippingRequest) {
		Shipping shipping = buildShippingFromShippingRequest(shippingRequest);
		Shipping savedShippingDetails = shippingRepository.save(shipping);
		
		return new ShippingResponse("Success!",200, String.valueOf(savedShippingDetails.getShipperID()), savedShippingDetails.getShipperName(), savedShippingDetails.getPhone());
		
	}

	private Shipping buildShippingFromShippingRequest(ShippingRequest shippingRequest) {
		Shipping shipping = new Shipping();
		shipping.setPhone(shippingRequest.getShipperNumber());
		shipping.setShipperName(shippingRequest.getShipperName());
		return shipping;
	}

}
