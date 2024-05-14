//package playground.service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import playground.entity.Order;
//import playground.entity.Product;
//import playground.repository.OrderRepository;
//import playground.repository.ProductRepository;
//import playground.response.OrderResponse;
//import playground.response.Response;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//
//	private final OrderRepository orderRepository;
//
//	private final ProductRepository productRepository;
//
//	public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
//		this.orderRepository = orderRepository;
//		this.productRepository = productRepository;
//	}
//
//	@Override
//	public ResponseEntity<?> getOrderById(int id) {
//		Optional<Order> order = orderRepository.findById(Integer.toString(id));
//
//		if (order.isEmpty()) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND)
//					.body(new Response("Product has not been found", HttpStatus.NOT_FOUND.value()));
//		}
//
//		return ResponseEntity.ok(order.get());
//	}
//
//	@Override
//	public ResponseEntity<?> createOrder(Order order) {
//
//		System.out.println("");
//		List<Product> productIdList = order.getProducts();
//		List<Product> allProducts = productRepository.findAll();
//
//		Set<Integer> allProductIds = allProducts.stream().map(Product::getProductId).collect(Collectors.toSet());
//
//		if (checkProductsExist(productIdList, allProductIds)) {
//			orderRepository.save(order);
//
//			return ResponseEntity.status(HttpStatus.OK)
//					.body(new Response("Order has been saved successfully!", HttpStatus.OK.value()));
//		}
//
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//				.body(new Response("Order contains non-existent products..", HttpStatus.BAD_REQUEST.value()));
//
//	}
//
//	@Override
//	public void deleteOrderById(int id) {
//		// TODO Auto-generated method stub
//
//	}
//
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
//
//}
