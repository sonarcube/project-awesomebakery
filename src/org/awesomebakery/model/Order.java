package org.awesomebakery.model;

public class Order {

	private String customerId;
	private Date orderDate;
	private Date deliveryDate;
	private int[] orders;

	public Order(String customerId, Date orderDate, Date deliveryDate, int[] orders) {
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.deliveryDate = deliveryDate;
		this.orders = orders;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int[] getOders() {
		return orders;
	}

	public void setOders(int[] oders) {
		this.orders = oders;
	}

}
