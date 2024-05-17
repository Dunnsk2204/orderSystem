package playground.response.models;

import java.util.List;

public class OrderProductResponse extends OrderResponse {

	private List<ProductResponse> products;
	
	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}

}
