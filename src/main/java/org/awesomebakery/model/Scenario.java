package org.awesomebakery.model;

import java.util.List;

import org.awesomebakery.utils.JsonListHelper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class Scenario {

	private List<Bakery> bakeries;
	private List<Customer> customers;
	private List<Order> orders;

	public static Scenario fromJson(JsonNode node) {
		Scenario scenario = new Scenario();
		scenario.bakeries = JsonListHelper.toObjectList(Bakery.class, (ArrayNode) node.get("bakeries"));
		scenario.customers = JsonListHelper.toObjectList(Customer.class, (ArrayNode) node.get("customers"));
		scenario.orders = JsonListHelper.toObjectList(Order.class, (ArrayNode) node.get("orders"));
		return scenario;
	}

	public List<Bakery> getBakeries() {
		return bakeries;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public List<Customer> getCustomers() {
		return customers;
	}
}
