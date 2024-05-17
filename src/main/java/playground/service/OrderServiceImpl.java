package playground.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
import playground.requests.OrderDetailRequest;
import playground.requests.OrderRequest;
import playground.response.models.OrderProductResponse;
import playground.response.models.OrderResponse;
import playground.response.models.ProductResponse;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final EmployeeRepository employeeRepository;

	private final CustomerRepository customerRepository;

	private final ShippingRepository shippingRepository;

	private final OrderDetailRepository orderDetailRepository;

	private final ProductRepository productRepository;

	private Map<String, OrderResponse> responseMap;

	public OrderServiceImpl(ProductRepository productRepository, OrderDetailRepository orderDetailRepository,
			OrderRepository orderRepository, EmployeeRepository employeeRepository,
			CustomerRepository customerRepository, ShippingRepository shippingRepository) {
		this.orderRepository = orderRepository;
		this.employeeRepository = employeeRepository;
		this.shippingRepository = shippingRepository;
		this.customerRepository = customerRepository;
		this.orderDetailRepository = orderDetailRepository;
		this.productRepository = productRepository;
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
	public Map<String, String> createOrder(OrderRequest orderRequest) {

		Map<String, String> response = new HashMap<String, String>();

		Optional<Customer> customer = customerRepository.findById(orderRequest.getCustomerId());
		Optional<Employee> employee = employeeRepository.findById(orderRequest.getEmployeeId());
		Optional<Shipping> shipping = shippingRepository.findById(orderRequest.getShipperId());

		if ((customer.isPresent()) && (employee.isPresent()) && (shipping.isPresent())) {

			Order savedOrder = orderRepository
					.save(getOrderFromRequest(orderRequest, customer.get(), employee.get(), shipping.get()));
			OrderResponse orderResponse = buildOrderResponseFromOrder(savedOrder);

			response.put("orderId", orderResponse.getOrderID());
			return response;
		}

		response.put("Failed to create Order.", "");
		return response;
	}

	@Override
	public Map<String, String> createOrderDetailsForOrder(OrderDetailRequest orderRequest) {
		Map<String, String> responseMap = new HashMap<>();

		orderRequest.getDetailRequestlist().forEach(orderDetailRequest -> {
			Optional<Product> productOptional = productRepository.findById(orderDetailRequest.getProductId());
			Optional<Order> orderOptional = orderRepository.findById(orderDetailRequest.getOrderId());

			if (productOptional.isPresent() && orderOptional.isPresent()) {
				Product product = productOptional.get();
				Order order = orderOptional.get();
				OrderDetail savedOrderDetail = saveOrderDetail(order, product, orderDetailRequest.getQuantity());

				responseMap.put("orderDetailId", String.valueOf(savedOrderDetail.getOrderDetailID()));
				responseMap.put("orderId", String.valueOf(savedOrderDetail.getOrder().getOrderID()));
			} else {
				responseMap.put("error", "Product or Order not found.");
			}
		});
		return responseMap;
	}

	@Override
	public Map<String, String> deleteOrderById(String id) {
		Map<String, String> responseMap = new HashMap<>();
		Optional<Order> order = orderRepository.findById(id);

		if (order.isEmpty()) {
			responseMap.put("ERROR", "No order exists with the orderId");
		}

		List<OrderDetail> details = orderDetailRepository.findAll().stream()
				.filter(x -> x.getOrder().getOrderID() == order.get().getOrderID()).toList();
		orderDetailRepository.deleteAll(details);
		orderRepository.delete(order.get());

		responseMap.put("Deleted Order:", String.valueOf(order.get().getOrderID()));
		return responseMap;
	}

	@Override
	public List<OrderResponse> getAllOrders() {

		List<Order> orderList = orderRepository.findAll();

		List<OrderResponse> orderResponseList = orderList.stream().map(order -> {
			OrderResponse orderResponse = new OrderResponse();
			orderResponse.setOrderDate(order.getOrderDate());
			orderResponse.setOrderID(String.valueOf(order.getOrderID()));
			orderResponse.setCustomer(order.getCustomer());
			orderResponse.setShipper(order.getShipper());
			orderResponse.setEmployee(order.getEmployee());
			return orderResponse;
		}).collect(Collectors.toList());

		return orderResponseList;
	}

	@Override
	public List<OrderProductResponse> getAllOrdersWithProducts() {
		List<Order> orderList = orderRepository.findAll();

		List<OrderProductResponse> orderProducts = orderList.stream().map(order -> {

			List<OrderDetail> orderDetails = orderDetailRepository.findAllByOrderID(String.valueOf(order.getOrderID()));

			List<ProductResponse> products = orderDetails.stream()
					.map(detail -> new ProductResponse(detail.getProduct().getProductID(),
							detail.getProduct().getProductName(), detail.getProduct().getSupplier(),
							String.valueOf(detail.getProduct().getPrice()), detail.getProduct().getCategory(),
							detail.getProduct().getUnit()))
					.collect(Collectors.toList());

			OrderProductResponse orderResponse = new OrderProductResponse();
			orderResponse.setOrderDate(order.getOrderDate());
			orderResponse.setOrderID(String.valueOf(order.getOrderID()));
			orderResponse.setCustomer(order.getCustomer());
			orderResponse.setShipper(order.getShipper());
//			orderResponse.setEmployee(order.getEmployee());
			orderResponse.setProducts(products);
			return orderResponse;
		}).collect(Collectors.toList());

		return orderProducts;

	}

	@Override
	public Order updateOrderByOrderId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	private OrderDetail saveOrderDetail(Order order, Product product, String quantity) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		orderDetail.setQuantity(Integer.parseInt(quantity));
		return orderDetailRepository.save(orderDetail);
	}

	private OrderResponse buildOrderResponseFromOrder(Order savedOrder) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderID(String.valueOf(savedOrder.getOrderID()));
		orderResponse.setCustomer(savedOrder.getCustomer());
		orderResponse.setEmployee(savedOrder.getEmployee());
		orderResponse.setShipper(savedOrder.getShipper());
		orderResponse.setOrderDate(savedOrder.getOrderDate());
		return orderResponse;
	}

	private Order getOrderFromRequest(OrderRequest orderRequest, Customer customer, Employee employee,
			Shipping shipping) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setEmployee(employee);
		order.setOrderDate(orderRequest.getOrderDate());
		order.setShipper(shipping);
		return order;
	}

	private OrderResponse createOrderResponse(Order order) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setCustomer(order.getCustomer());
		orderResponse.setEmployee(order.getEmployee());
		orderResponse.setOrderDate(order.getOrderDate());
		orderResponse.setOrderID(String.valueOf(order.getOrderID()));
		orderResponse.setShipper(order.getShipper());
		return orderResponse;
	}

}
