package playground.service;

import java.util.Map;

import playground.requests.OrderRequest;
import playground.response.models.OrderResponse;

public interface OrderService {
	
	Map<String, OrderResponse> getOrderById(int id);
	
	Map<String, OrderResponse> createOrder(OrderRequest order);
//	
//	void deleteOrderById(int id);
//	
//	Order updateOrderByOrderId(int id);
//	
//	List<OrderResponse> getAllOrders();

}
