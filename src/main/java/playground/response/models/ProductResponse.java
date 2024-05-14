package playground.response.models;

import java.math.BigDecimal;

import playground.entity.Category;
import playground.entity.Supplier;

public class ProductResponse {

	private int productId;
	private String name;
	private Supplier supplier;
	private String price;
	private Category category;
	private String unit;
	
	public ProductResponse(int productId, String name, Supplier supplier, String price, Category category,String unit) {
		super();
		this.productId = productId;
		this.name = name;
		this.supplier = supplier;
		this.price = price;
		this.category = category;
		this.unit = unit;
	}
	
	public ProductResponse() {
		
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
