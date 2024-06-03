package playground.service;

import java.util.List;

import playground.requests.CustomerRequest;
import playground.response.CustomerResponse;

public interface CustomerService {
	
	List<CustomerResponse> getAllCustomers();
	
	CustomerResponse getCustomer(String id);
	
	CustomerResponse saveNewCustomer(CustomerRequest customer);
	
	List<CustomerResponse> getCustomersByCity(String city);
	

}
