package org.awesomebakery.model;

import java.util.List;
import java.util.Vector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class Scenario {
	
	private List<Product> products = new Vector<>();
	private List<Order> orders = new Vector<>();
	
	public static Scenario fromJson(JsonNode node) {
		Scenario scenario = new Scenario();
		ArrayNode productsNode = (ArrayNode)node.get("products");
		for (JsonNode productNode : productsNode) {
			Product product = Product.fromJson(productNode);
			scenario.products.add(product);
		}
		
		ArrayNode ordersNode = (ArrayNode)node.get("orders");
		for (JsonNode orderNode : ordersNode) {
			Order order = Order.fromJson(orderNode);
			scenario.orders.add(order);
		}
		return scenario;
	}
}
