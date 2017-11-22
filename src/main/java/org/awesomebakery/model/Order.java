package org.awesomebakery.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class Order extends Entity {

	private String customerId;
	private Date orderDate;
	private Date deliveryDate;
	private Map<String, Integer> products;

	public Order(String guid) {
		super(guid);
	}

	public static Order fromJson(JsonNode node) {
		Order order = new Order(node.get("guid").asText());
		order.customerId = node.get("customer_id").asText();
		order.deliveryDate = Date.fromJson(node.get("delivery_date"));
		order.orderDate = Date.fromJson(node.get("order_date"));
		order.products = new HashMap<>();
		JsonNode products = node.get("products");
		Iterator<String> productIterator = products.fieldNames();
		while (productIterator.hasNext()) {
			String productName = productIterator.next();
			order.products.put(productName, products.get(productName).asInt());
		}
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

	public Map<String, Integer> getProducts() {
		return products;
	}
	
	@Override
	public String toString() {
		return "[" + customerId + "," + orderDate + "," + deliveryDate + "," + products.toString() + "]";
	}

}
