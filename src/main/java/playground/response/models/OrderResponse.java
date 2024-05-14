package playground.response.models;

import java.util.Date;

import playground.entity.Customer;
import playground.entity.Employee;
import playground.entity.Shipping;

public class OrderResponse {
	
	private int orderID;

	private Customer customer;

	private Employee employee;

	private Date orderDate;

	private Shipping shipper;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Shipping getShipper() {
		return shipper;
	}

	public void setShipper(Shipping shipper) {
		this.shipper = shipper;
	}
	
    
}
