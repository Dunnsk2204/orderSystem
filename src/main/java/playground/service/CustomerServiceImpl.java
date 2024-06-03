package playground.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import playground.entity.Customer;
import playground.repository.CustomerRepository;
import playground.requests.CustomerRequest;
import playground.response.CustomerResponse;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {
	    List<Customer> customerList = customerRepository.findAll();
	    return createCustomerResponses(customerList);
	}

	@Override
	public List<CustomerResponse> getCustomersByCity(String city) {
	    if (city.isEmpty() || city.length() <= 2) {
	        return List.of(new CustomerResponse("City is empty", 204));
	    }

	    List<Customer> customerList = customerRepository.findAll().stream()
	            .filter(customer -> customer.getCity().contains(city))
	            .collect(Collectors.toList());

	    return createCustomerResponses(customerList);
	}

	
	@Override
	public CustomerResponse getCustomer(String id) {
		Optional<Customer> customer = customerRepository.findById(id); 
		
		if (customer.isEmpty()) {
			return new CustomerResponse("No Customer Found", 204);
		}
		
		 return createCustomerResponse(customer.get());
	}

	@Override
	public CustomerResponse saveNewCustomer(CustomerRequest customerRequest) {
		
		Customer customer = buildCustomerFromRequest(customerRequest);
		Customer savedCustomer = customerRepository.save(customer);
		
		return createCustomerResponse(savedCustomer);
	}
	
	private Customer buildCustomerFromRequest(CustomerRequest customerRequest) {
		Customer customer = new Customer();
		customer.setContactName(customerRequest.getContactName());
		customer.setCustomerName(customerRequest.getCustomerName());
		customer.setAddress(customerRequest.getStreetAddress());
		customer.setCity(customerRequest.getCity());
		customer.setPostalCode(customerRequest.getPostcode());
		customer.setCountry(customerRequest.getCountry());
		return customer;
	}

	private List<CustomerResponse> createCustomerResponses(List<Customer> customers) {
	    return customers.stream()
	            .map(c -> new CustomerResponse("Success!", 200, c.getCustomerName(), c.getAddress(), c.getCity(),
	                    c.getPostalCode(), c.getCountry(), c.getContactName()))
	            .collect(Collectors.toList());
	}
	
	private CustomerResponse createCustomerResponse(Customer c) {
	    return new CustomerResponse("Success!", 200, c.getCustomerName(), c.getAddress(), c.getCity(),
	                    c.getPostalCode(), c.getCountry(), c.getContactName());
	}

}
