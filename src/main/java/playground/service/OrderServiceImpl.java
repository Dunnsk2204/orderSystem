package playground.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import playground.entity.Customer;
import playground.entity.Employee;
import playground.entity.Order;
import playground.entity.Shipping;
import playground.repository.CustomerRepository;
import playground.repository.EmployeeRepository;
import playground.repository.OrderRepository;
import playground.repository.ShippingRepository;
import playground.requests.OrderRequest;
import playground.response.models.OrderResponse;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final EmployeeRepository employeeRepository;
	
	private final CustomerRepository customerRepository;
	
	private final ShippingRepository shippingRepository;

	
	private Map<String, OrderResponse> responseMap;

	public OrderServiceImpl(OrderRepository orderRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository, ShippingRepository shippingRepository) {
		this.orderRepository = orderRepository;
		this.employeeRepository = employeeRepository;
		this.shippingRepository = shippingRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public Map<String, OrderResponse> getOrderById(int id) {
		Optional<Order> order = orderRepository.findById(Integer.toString(id));
		responseMap = new HashMap<>();


		if (!order.isPresent()) {
			responseMap.put("No order is available with the ID " + id, new OrderResponse());
			return responseMap;
		}
		
		OrderResponse orderResponse = createOrderResponse(order.get());
		responseMap.put(HttpStatus.OK.getReasonPhrase(), orderResponse);
		

		return responseMap;
	}

	@Override
	public Map<String, OrderResponse>createOrder(OrderRequest orderRequest) {
		
		Optional<Customer> customer = customerRepository.findById(orderRequest.getCustomerId());
		Optional<Employee> employee = employeeRepository.findById(orderRequest.getEmployeeId());
		Optional<Shipping> shipping = shippingRepository.findById(orderRequest.getShipperId());
		responseMap = new HashMap<>();


		if ((customer.isPresent()) && (employee.isPresent()) && (shipping.isPresent())) {
			
			Order savedOrder = orderRepository.save(getOrderFromRequest(orderRequest, customer.get(), employee.get(), shipping.get()));
			OrderResponse orderResponse = buildOrderResponseFromOrder(savedOrder);
			responseMap.put("Success!", orderResponse);
			return responseMap;
		}
		
		responseMap.put("Failed to create Order.", new OrderResponse());
		return responseMap;
		

	}

	private OrderResponse buildOrderResponseFromOrder(Order savedOrder) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderID(savedOrder.getOrderID());
		orderResponse.setCustomer(savedOrder.getCustomer());
		orderResponse.setEmployee(savedOrder.getEmployee());
		orderResponse.setShipper(savedOrder.getShipper());
		orderResponse.setOrderDate(savedOrder.getOrderDate());
		return orderResponse;
	}

	private Order getOrderFromRequest(OrderRequest orderRequest, Customer customer, Employee employee, Shipping shipping) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setEmployee(employee);
		order.setOrderDate(orderRequest.getOrderDate());
		order.setShipper(shipping);
		return order;
	}

//	@Override
//	public void deleteOrderById(int id) {
//		// TODO Auto-generated method stub
//
//	}

//	@Override
//	public Order updateOrderByOrderId(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<OrderResponse> getAllOrders() {
//
//		List<Order> orderList = orderRepository.findAll();
//
//		List<OrderResponse> orderResponseList = orderList.stream().map(order -> {
//			OrderResponse orderResponse = new OrderResponse();
//			orderResponse.setOrderDate(order.getOrderDate());
//			orderResponse.setOrderId(order.getOrderId());
//			orderResponse.setTotalAmount(order.getTotalAmount());
//			orderResponse.setUser(order.getUser());
//			return orderResponse;
//		}).collect(Collectors.toList());
//
//		return orderResponseList;
//	}
//	
//	
//	private boolean checkProductsExist(List<Product> orderProductList, Set<Integer> allProductsList) {
//		for (Product productOrder : orderProductList) {
//			if (!allProductsList.contains(productOrder.getProductId())) {
//				return false;
//			}
//		}
//		return true;
//	}
	
	
	private OrderResponse createOrderResponse(Order order) {
	    OrderResponse orderResponse = new OrderResponse();
	    orderResponse.setCustomer(order.getCustomer());
	    orderResponse.setEmployee(order.getEmployee());
	    orderResponse.setOrderDate(order.getOrderDate());
	    orderResponse.setOrderID(order.getOrderID());
	    orderResponse.setShipper(order.getShipper());
	    return orderResponse;
	}

}
