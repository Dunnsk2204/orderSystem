package playground.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import playground.requests.ProductRequest;
import playground.response.models.ProductResponse;
import playground.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	List<ProductResponse> allProducts() {
		return this.productService.getAllProducts();
	}

	@GetMapping("/{id}")
	ResponseEntity<?> getProductById(@PathVariable String id) {
		return this.productService.getProductById(Integer.valueOf(id));
	}

	@PostMapping("/createProduct")
	Map<String, ProductResponse> postUser(@RequestBody ProductRequest product) {
		Map<String,ProductResponse> response = productService.saveProduct(product);
		return response;
	}

	
	
	

}
