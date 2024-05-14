//package playground.controller;
//
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import playground.entity.Order;
//import playground.response.OrderResponse;
//import playground.service.OrderService;
//
//@RestController
//@RequestMapping("v1/orders")
//public class OrderController {
//
//	private final OrderService orderService;
//
//	public OrderController(OrderService orderService) {
//		this.orderService = orderService;
//	}
//
//	@GetMapping("/{id}")
//	ResponseEntity<?> getOrderById(@PathVariable String id) {
//		return orderService.getOrderById(Integer.valueOf(id));
//	}
//	
//	@GetMapping("/getAllOrders")
//	List<OrderResponse> getOrders() {
//		return orderService.getAllOrders();
//	}
//	
//	@PostMapping("/createOrder")
//	ResponseEntity<?> createOrders(@RequestBody Order order) {
//		return orderService.createOrder(order);
//	}
//
//}
