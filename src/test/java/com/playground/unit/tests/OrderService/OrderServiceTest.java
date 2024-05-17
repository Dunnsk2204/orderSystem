package com.playground.unit.tests.OrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;

import playground.entity.Customer;
import playground.entity.Employee;
import playground.entity.Order;
import playground.entity.Shipping;
import playground.repository.CustomerRepository;
import playground.repository.EmployeeRepository;
import playground.repository.OrderDetailRepository;
import playground.repository.OrderRepository;
import playground.repository.ProductRepository;
import playground.repository.ShippingRepository;
import playground.requests.OrderRequest;
import playground.response.models.OrderResponse;
import playground.service.OrderService;
import playground.service.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@Mock
	private OrderRepository orderRepository;

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private CustomerRepository customerRepository;

	@Mock
	private ShippingRepository shippingRepository;

	@Mock
	private OrderDetailRepository orderDetailRepository;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private OrderServiceImpl orderService;
	
    private OrderRequest orderRequest;
    private Customer customer;
    private Employee employee;
    private Shipping shipping;
    private Order order;
    private Date date;
	
    @BeforeEach
    public void setUp() {
        orderRequest = new OrderRequest();
        orderRequest.setCustomerId("1");
        orderRequest.setEmployeeId("2");
        orderRequest.setShipperId("3");
        date = new Date();
        orderRequest.setOrderDate(date);

        customer = new Customer();
        employee = new Employee();
        shipping = new Shipping();
        order = new Order();
        order.setOrderID(4);
    }

    @Test
    public void testCreateOrder_Success() {
        // Mock the repository methods
        when(customerRepository.findById("1")).thenReturn(Optional.of(customer));
        when(employeeRepository.findById("2")).thenReturn(Optional.of(employee));
        when(shippingRepository.findById("3")).thenReturn(Optional.of(shipping));
        when(orderRepository.save(Mockito.any(Order.class))).thenReturn(order);

        // Call the method under test
        Map<String, String> response = orderService.createOrder(orderRequest);

        // Assertions
        assertNotNull(response);
        assertTrue(response.containsKey("orderId"));
        assertEquals("4", response.get("orderId"));
    }

	@Test
	public void assertGetOrderById() {

		// Given
		final int orderId = 2;
		List<Order> orderList = createTestData();
		when(orderRepository.findById(String.valueOf(orderId))).thenReturn(Optional.of(orderList.get(1)));

		// When
        Map<String, OrderResponse> result = orderService.getOrderById(orderId);
		
		// Then
        assertEquals(1, result.size());
        assertEquals(HttpStatus.OK.getReasonPhrase(), result.keySet().iterator().next());
        
        OrderResponse orderResponse = result.get(HttpStatus.OK.getReasonPhrase());
        assertEquals("Dan Robins", orderResponse.getCustomer().getContactName());
        assertEquals("Sarah Smith", orderResponse.getEmployee().getFirstName());
        assertEquals(String.valueOf(orderId), orderResponse.getOrderID());
        
        verify(orderRepository, times(1)).findById(Integer.toString(orderId));
	}
	
	@Test
	public void assertGetAllOrders() {
		
		// Given
		List<Order> orderList = createTestData();
		when(orderRepository.findAll()).thenReturn(orderList);
		
		// When
        List<OrderResponse> result = orderService.getAllOrders();

        
        // Then
        assertEquals(3, result.size());  
        assertEquals("Johnny Rotten", result.get(0).getCustomer().getContactName());
        assertEquals("Dan Robins", result.get(1).getCustomer().getContactName());
        assertEquals("John Yates", result.get(2).getCustomer().getContactName());
	}
	
//	  @Test
//	    public void testCreateOrder_Success() {
//		  	Date date = new Date();
//		  	
//	        // Mocking the OrderRequest
//	        OrderRequest orderRequest = new OrderRequest();
//	        orderRequest.setCustomerId("112233");
//	        orderRequest.setEmployeeId("21322");
//	        orderRequest.setShipperId("23480");
//	        orderRequest.setOrderDate(date);
//
//	        // Mocking the repositories
//	        Customer customer = new Customer();
//	        customer.setCustomerID(112233);
//	        
//	        Employee employee = new Employee();
//	        employee.setEmployeeID(21322);
//	        
//	        Shipping shipping = new Shipping();
//	        shipping.setShipperID(23480);
//	        
//	        Order order = new Order();
//	        order.setOrderID(400009);
//	        order.setCustomer(customer);
//	        order.setEmployee(employee);
//	        order.setOrderDate(date);
//	        order.setShipper(shipping);
//
//	        when(customerRepository.findById(orderRequest.getCustomerId())).thenReturn(Optional.of(customer));
//	        when(employeeRepository.findById(orderRequest.getEmployeeId())).thenReturn(Optional.of(employee));
//	        when(shippingRepository.findById(orderRequest.getShipperId())).thenReturn(Optional.of(shipping));
//	        when(orderRepository.save(any(Order.class))).thenReturn(order);
//
//	        // Call the method
//	        Map<String, String> response = orderService.createOrder(orderRequest);
//
//	        // Assertions
//	        assertNotNull(response);
//	        assertTrue(response.keySet().contains("orderId"));
//	        assertEquals("400009", response.get("orderId"));
//	    }
	
	@Test
	public void testCreateOrderDetailsForOrder() {
		
	}
	
	@Test
	public void testDeleteOrderById() {
		
	}

	private static Customer createCustomer(int id, String name) {
		Customer customer = new Customer();
		customer.setCustomerID(id);
		customer.setContactName(name);
		return customer;
	}

	private static Employee createEmployee(int id, String name) {
		Employee employee = new Employee();
		employee.setEmployeeID(id);
		employee.setFirstName(name);
		return employee;
	}

	private static Shipping createShipping(int id, String name, String phone) {
		Shipping shipping = new Shipping();
		shipping.setShipperID(id);
		shipping.setShipperName(name);
		shipping.setPhone(phone);
		return shipping;
	}
	
    @Configuration
    @Import(OrderService.class)
    static class TestConfig {
    	
    }

    public static List<Order> createTestData() {
    	List<Order> testData = new ArrayList<>();

		// Order 1
		Order order1 = new Order();
		order1.setOrderID(1);
		order1.setCustomer(createCustomer(1, "Johnny Rotten"));
		order1.setEmployee(createEmployee(1, "Laura Smith"));
		order1.setOrderDate(new Date());
		order1.setShipper(createShipping(2, "Amazon", "02043445554"));
		testData.add(order1);

		// Order 2
		Order order2 = new Order();
		order2.setOrderID(2);
		order2.setCustomer(createCustomer(3, "Dan Robins"));
		order2.setEmployee(createEmployee(5, "Sarah Smith"));
		order2.setOrderDate(new Date());
		order2.setShipper(createShipping(3, "UPS", "123456789"));
		testData.add(order2);

		// Order 3
		Order order3 = new Order();
		order3.setOrderID(3);
		order3.setCustomer(createCustomer(45, "John Yates"));
		order3.setEmployee(createEmployee(8, "Mary Jones"));
		order3.setOrderDate(new Date());
		order3.setShipper(createShipping(12, "Royal Mail", "987654321"));
		testData.add(order3);

		return testData;
    }
}
