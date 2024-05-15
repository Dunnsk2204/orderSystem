package playground.requests;

import java.util.Date;
import java.util.List;

public class OrderRequest {
	
//	private int orderID;

	private String customerId;
	
	private String employeeId;

	private Date orderDate;

	private String shipperId;
	
	List<OrderDetailRequest> orderDetail;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getShipperId() {
		return shipperId;
	}

	public void setShipperId(String shipperId) {
		this.shipperId = shipperId;
	}

	public List<OrderDetailRequest> getOrderDetailRequest() {
		return orderDetail;
	}

	public void setOrderDetailRequest(List<OrderDetailRequest> orderDetail) {
		this.orderDetail = orderDetail;
	}

}
