package org.awesomebakery.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Order {

	private String customerId;
	private Date orderDate;
	private Date deliveryDate;
	private int[] orders;

	public static Order fromJson(JsonNode node) {
		Order order = new Order();
		order.customerId = node.get("customer-id").asText();
		order.deliveryDate = Date.fromJson(node.get("delivery-date"));
		order.orderDate = Date.fromJson(node.get("order-date"));
		return order;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public int[] getOders() {
		return orders;
	}

}
