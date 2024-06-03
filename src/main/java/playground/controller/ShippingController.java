package playground.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import playground.entity.Shipping;
import playground.requests.ShippingRequest;
import playground.response.ShippingResponse;
import playground.service.ShippingService;

@RestController
@RequestMapping("shipping")
public class ShippingController {
	
	private final ShippingService shippingService;
	
	public ShippingController(ShippingService shippingService) {
		this.shippingService = shippingService;
	}
	
	@GetMapping("/allShipping")
	public List<Shipping> getAllShippers() {
		return this.shippingService.getAllShippingDetails();
	}
	
	@GetMapping("/{id}")
	public Shipping getShippingDetailsById(@PathVariable String id) {
		return this.shippingService.getShippingDetailsById(id);
	}
	
	@PostMapping("/newShipping")
	public ShippingResponse saveNewShippingDetails(@RequestBody ShippingRequest request) {
		return this.shippingService.saveNewShippingDetails(request);
	}

}
