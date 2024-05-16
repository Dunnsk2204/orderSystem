package playground.service;

import java.util.List;
import java.util.Map;

import playground.entity.Order;
import playground.requests.OrderDetailRequest;
import playground.requests.OrderRequest;
import playground.response.models.OrderResponse;

public interface OrderService {
	
	Map<String, OrderResponse> getOrderById(int id);
	
	Map<String, String> createOrder(OrderRequest order);
	
	Map<String, String> createOrderDetailsForOrder(OrderDetailRequest orderRequest);

	Map<String, String> deleteOrderById(String id);
	
	Order updateOrderByOrderId(int id);
	
	List<OrderResponse> getAllOrders();

}
