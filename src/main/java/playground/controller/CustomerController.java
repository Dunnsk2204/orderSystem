package playground.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import playground.requests.CustomerRequest;
import playground.response.CustomerResponse;
import playground.service.CustomerService;

@RestController
@RequestMapping("customers")
public class CustomerController {

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/getCustomers")
	public List<CustomerResponse> getCustomers() {
		return this.customerService.getAllCustomers();
	}
	
	@GetMapping("/getCustomers/{city}")
	public List<CustomerResponse> getCustomersByCityName(@PathVariable String city) {
		return this.customerService.getCustomersByCity(city);
	}
	
	@GetMapping("/{id}")
	public CustomerResponse getCustomerById(@PathVariable String id) {
		return customerService.getCustomer(id);
	}
	
	@PostMapping("/addCustomer")
	public CustomerResponse createCustomer(@RequestBody CustomerRequest request) {
		return customerService.saveNewCustomer(request);
	}
}
