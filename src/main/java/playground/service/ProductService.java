package playground.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import playground.entity.Product;
import playground.requests.ProductRequest;
import playground.response.models.ProductResponse;

public interface ProductService {
	
	List<ProductResponse> getAllProducts();
	
	ResponseEntity<?> getProductById(int id);
	    
    void deleteProductById(int productId);

	Map<String, ProductResponse> saveProduct(ProductRequest productRequest);

}
