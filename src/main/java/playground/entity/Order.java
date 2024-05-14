
package playground.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderID")
	private int orderID;

	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "EmployeeID")
	private Employee employee;

	@Column(name = "OrderDate")
	private Date orderDate;

	@ManyToOne
	@JoinColumn(name = "ShipperID")
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
