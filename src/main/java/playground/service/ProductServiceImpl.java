package playground.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import playground.entity.Category;
import playground.entity.Product;
import playground.entity.Supplier;
import playground.repository.CategoryRepository;
import playground.repository.ProductRepository;
import playground.repository.SupplierRepository;
import playground.requests.ProductRequest;
import playground.response.Response;
import playground.response.models.ProductResponse;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final SupplierRepository supplierRepository;

	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
			SupplierRepository supplierRepository) {
		this.productRepository = productRepository;
		this.supplierRepository = supplierRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> productList = productRepository.findAll();
		return productList.stream().map(this::mapProductToResponse).collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<?> getProductById(int id) {
		Optional<Product> optionalProduct = productRepository.findById(Integer.toString(id));

		if (optionalProduct.isPresent()) {
			ProductResponse productResponse = mapProductToResponse(optionalProduct.get());
			return ResponseEntity.ok(productResponse);
		}

		Response errorResponse = new Response("Product has not been found", HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@Override
	public Map<String, ProductResponse> saveProduct(ProductRequest productRequest) {

		Optional<Category> categoryOptional = categoryRepository.findById(productRequest.getCategoryId());
		Optional<Supplier> supplierOptional = supplierRepository.findById(productRequest.getSupplierId());
		Map<String, ProductResponse> response = new HashMap<>();

		if (categoryOptional.isEmpty() || supplierOptional.isEmpty()) {
			response.put("Error, no supplier or category with the IDs received.", new ProductResponse());
			return response;
		}

		if ((productRequest.getProductName().isBlank()) || (productRequest.getProductName().isEmpty())
				|| productRequest.getProductName() == null) {
			response.put("Error, Product must have a name.", new ProductResponse());
			return response;
		}

		Product product = createProductFromRequest(productRequest, categoryOptional.get(), supplierOptional.get());
		Product savedProduct = productRepository.save(product);

		Map<String, ProductResponse> returnResponse = new HashMap<>();
		returnResponse.put("Success", mapProductToResponse(savedProduct));
		return returnResponse;
	}

	@Override
	public List<ProductResponse> getAllProductsByCategoryId(int categoryId) {
		List<Product> productList = productRepository.findAll().stream()
				.filter(x -> x.getCategory().getCategoryID() == categoryId).collect(Collectors.toList());
		
		return productList.stream().map(this::mapProductToResponse).collect(Collectors.toList());
	}

	@Override
	public void deleteProductById(int productId) {
		productRepository.deleteById(Integer.toString(productId));
	}

	/**
	 * Helper Functions to build request/response objects.
	 */
	private Product createProductFromRequest(ProductRequest productRequest, Category category, Supplier supplier) {
		Product product = new Product();
		product.setCategory(category);
		product.setPrice(productRequest.getPrice());
		product.setProductName(productRequest.getProductName());
		product.setSupplier(supplier);
		product.setUnit(productRequest.getUnit());
		return product;
	}

	private ProductResponse mapProductToResponse(Product product) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setProductId(product.getProductID());
		productResponse.setName(product.getProductName());
		productResponse.setPrice(product.getPrice().toString());
		productResponse.setSupplier(product.getSupplier());
		productResponse.setCategory(product.getCategory());
		productResponse.setUnit(product.getUnit());
		return productResponse;
	}

}
