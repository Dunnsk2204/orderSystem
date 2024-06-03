package com.playground.unit.tests.OrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.http.HttpStatus;

import playground.entity.Customer;
import playground.entity.Employee;
import playground.entity.Order;
import playground.entity.OrderDetail;
import playground.entity.Product;
import playground.entity.Shipping;
import playground.repository.CustomerRepository;
import playground.repository.EmployeeRepository;
import playground.repository.OrderDetailRepository;
import playground.repository.OrderRepository;
import playground.repository.ProductRepository;
import playground.repository.ShippingRepository;
import playground.requests.DetailRequest;
import playground.requests.OrderDetailRequest;
import playground.requests.OrderRequest;
import playground.response.models.OrderResponse;
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
    private Product product;
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
    public void testCreateOrder_Failure_CustomerNotFound() {
        // Mock the repository methods
        when(customerRepository.findById("1")).thenReturn(Optional.empty());
        when(employeeRepository.findById("2")).thenReturn(Optional.of(employee));
        when(shippingRepository.findById("3")).thenReturn(Optional.of(shipping));
        
        // Call the method under test
        Map<String, String> response = orderService.createOrder(orderRequest);

        // Assertions
        assertNotNull(response);
        assertFalse(response.containsKey("orderId"));
        assertTrue(response.containsKey("Failed to create Order."));
    }
    
//    @Test
//    public void testCreateOrderDetailsForOrder_Success() {
//        // Mock the repositories
//    	customer.setCustomerID(101);
//    	customer.setCustomerName("Test");
//    	
//    	product.setProductID(101);
//    	product.setProductName("Test McTest");
//    	
//    	employee.setEmployeeID(123);
//    	employee.setFirstName("Jane");
//    	shipping.setShipperID(876);
//    	
//    	order.setOrderID(12345);
//    	order.setOrderDate(new Date());
//    	order.setCustomer(customer);
//    	order.setEmployee(employee);
//    	order.setShipper(shipping);
//    	
//        when(productRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(product));
//        when(orderRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(order));
//        
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrder(order);
//        orderDetail.setOrderDetailID(12345);
//        orderDetail.setProduct(product);
//        orderDetail.setQuantity(3);
//        
//        
//        when(orderService.saveOrderDetail(order, product, "10")).thenReturn(orderDetail);
//
//        // Create the request
//        List<OrderDetailRequest> orderDetailRequest = new ArrayList<OrderDetailRequest>();
//        OrderDetail request = new OrderDetail();
//        request.setOrder(order);
//        request.setProduct(product);
//        request.setQuantity(10);
//        request.setOrderDetailID(1234);
//        
//        orderDetailRequest.add(request);
//        orderRequest.setOrderDetailRequest(orderDetailRequest);
//
//        // Call the method
//        Map<String, String> response = orderService.createOrderDetailsForOrder(orderRequest);
//
//        // Assert the results
//        assertEquals("1", response.get("orderDetailId"));
//        assertEquals("1", response.get("orderId"));
//        verify(productRepository, times(1)).findById(1L);
//        verify(orderRepository, times(1)).findById(1L);
//    }
    
    
    @Test
    public void testCreateOrderDetailsForOrder_Success() {
        // Mock the repositories
    	product = new Product();
    	order = new Order();
        when(productRepository.findById("1")).thenReturn(Optional.of(product));
        when(orderRepository.findById("1")).thenReturn(Optional.of(order));
        when(orderService.saveOrderDetail(order, product, "10")).thenReturn(new OrderDetail());

        // Create the request
        OrderDetailRequest orderRequest = new OrderDetailRequest();
        DetailRequest detailRequest = new DetailRequest();
        detailRequest.setOrderId("1");
        detailRequest.setProductId("12");
        detailRequest.setQuantity("10");
        
        orderRequest.setDetailRequestlist(Arrays.asList(detailRequest));

        // Call the method
        Map<String, String> response = orderService.createOrderDetailsForOrder(orderRequest);

        // Assert the results
        assertEquals("1", response.get("orderDetailId"));
        assertEquals("1", response.get("orderId"));
    }
    
    @Test
    public void testCreateOrder_Failure_EmployeeNotFound() {
        // Mock the repository methods
        when(customerRepository.findById("1")).thenReturn(Optional.of(customer));
        when(employeeRepository.findById("2")).thenReturn(Optional.empty());
        when(shippingRepository.findById("3")).thenReturn(Optional.of(shipping));
        
        // Call the method under test
        Map<String, String> response = orderService.createOrder(orderRequest);

        // Assertions
        assertNotNull(response);
        assertFalse(response.containsKey("orderId"));
        assertTrue(response.containsKey("Failed to create Order."));
    }
    
    @Test
    public void testCreateOrder_Failure_ShippingNotFound() {
        // Mock the repository methods
        when(customerRepository.findById("1")).thenReturn(Optional.empty());
        when(employeeRepository.findById("2")).thenReturn(Optional.of(employee));
        when(shippingRepository.findById("3")).thenReturn(Optional.of(shipping));
        
        // Call the method under test
        Map<String, String> response = orderService.createOrder(orderRequest);

        // Assertions
        assertNotNull(response);
        assertFalse(response.containsKey("orderId"));
        assertTrue(response.containsKey("Failed to create Order."));
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
	public void assert_Failure_GetOrderById() {

		// Given
		when(orderRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

		// When
        Map<String, OrderResponse> result = orderService.getOrderById(Mockito.anyInt());
		
		// Then
        assertNotNull(result);
        assertTrue(result.containsKey("ERROR"));        
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
	
//	@Test
//    public void testGetAllOrdersWithProducts() {
//		
//		order = new Order();
//        order.setOrderID(1);
//        order.setOrderDate(new Date());
//        // order.setCustomer(customer); // Assuming customer is already set
//        // order.setEmployee(employee); // Assuming employee is already set
//        // order.setShipper(shipper); // Assuming shipper is already set
//
//        // Set up Product
//        product = new Product();
//        product.setProductID(1);
//        product.setProductName("Test Product");
//        product.setSupplier(676);
//        product.setPrice(BigDecimal.valueOf(122.99));
//        product.setCategory("Test Category");
//        product.setUnit("Test Unit");
//
//        // Set up OrderDetail
//        orderDetail = new OrderDetail();
//        orderDetail.setOrder(order);
//        orderDetail.setProduct(product);
//        orderDetail.setQuantity(1);
//        
//        // Mock the repository methods
//        when(orderRepository.findAll()).thenReturn(Arrays.asList(order));
//        when(orderDetailRepository.findAllByOrderID(String.valueOf(order.getOrderID())))
//                .thenReturn(Arrays.asList(orderDetail));
//
//        // Call the method under test
//        List<OrderProductResponse> responses = orderService.getAllOrdersWithProducts();
//
//        // Verify the interactions with the mocks
//        verify(orderRepository).findAll();
//        verify(orderDetailRepository).findAllByOrderID(String.valueOf(order.getOrderID()));
//
//        // Assertions
//        assertNotNull(responses);
//        assertEquals(1, responses.size());
//
//        OrderProductResponse response = responses.get(0);
//        assertEquals(String.valueOf(order.getOrderID()), response.getOrderID());
//        assertEquals(order.getOrderDate(), response.getOrderDate());
//        assertEquals(order.getCustomer(), response.getCustomer());
//        assertEquals(order.getShipper(), response.getShipper());
//        // assertEquals(order.getEmployee(), response.getEmployee());
//
//        List<ProductResponse> products = response.getProducts();
//        assertNotNull(products);
//        assertEquals(1, products.size());
//
//        ProductResponse productResponse = products.get(0);
//        assertEquals(product.getProductID(), productResponse.getProductID());
//        assertEquals(product.getProductName(), productResponse.getProductName());
//        assertEquals(product.getSupplier(), productResponse.getSupplier());
//        assertEquals(String.valueOf(product.getPrice()), productResponse.getPrice());
//        assertEquals(product.getCategory(), productResponse.getCategory());
//        assertEquals(product.getUnit(), productResponse.getUnit());
//    }
	
	
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
	
//    @Configuration
//    @Import(OrderService.class)
//    static class TestConfig {
//    	
//    }

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
