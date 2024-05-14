package playground.requests;

import java.math.BigDecimal;

public class ProductRequest {
	
//	private int productID;

	private String productName;

	private String supplierId;

	private String categoryId;

	private String unit;

	private BigDecimal price;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplier) {
		this.supplierId = supplier;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String category) {
		this.categoryId = category;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


}
