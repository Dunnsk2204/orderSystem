package com.playground.unit.tests.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import playground.entity.Category;
import playground.entity.Product;
import playground.entity.Supplier;
import playground.repository.CategoryRepository;
import playground.repository.ProductRepository;
import playground.repository.SupplierRepository;
import playground.response.models.ProductResponse;
import playground.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private SupplierRepository supplierRepository;
	
	@InjectMocks
	private ProductServiceImpl productService;
	
	private Product product;
	
	private Supplier supplier;
	
	private Category category;
	
	@BeforeEach
	public void setup() {
		supplier = new Supplier();
		supplier.setSupplierID(1);
		
		category = new Category();
		category.setCategoryID(2);	
		
		product = new Product();
		product.setSupplier(supplier);
		product.setCategory(category);
		product.setPrice(new BigDecimal(12.99));
		product.setProductID(1);
		product.setProductName("Test1");
		product.setUnit("12 boxes");
	}
	
	@Test
	public void testGetAllProductsSuccess() {
		
		// Given		
		Supplier supplier2 = new Supplier();
		supplier2.setSupplierID(121);
		
		Category category2 = new Category();
		category2.setCategoryID(12);
		
		Product product2 = new Product();
		product2.setCategory(category2);
		product2.setSupplier(supplier2);
		product2.setPrice(new BigDecimal(18.99));
		product2.setProductID(1);
		product2.setProductName("Test2");
		product2.setUnit("1 x box");
		
		when(productRepository.findAll()).thenReturn(Arrays.asList(product, product2));
		
		// When
		List<ProductResponse> productResponse = productService.getAllProducts();
		
		// Then
		assertEquals(2, productResponse.size());
		assertEquals("Test2", productResponse.get(1).getName());
		
		
		
		
	}
}
