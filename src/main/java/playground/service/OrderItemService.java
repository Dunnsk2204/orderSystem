package playground.service;

import org.springframework.http.ResponseEntity;

public interface OrderItemService {
	
	ResponseEntity<?> getOrderItemById(int id);
	
//	void create

}
