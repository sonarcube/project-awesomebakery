package org.awesomebakery.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Customer extends Entity {
	private String name;
	private String type;
	private Location location;

	public Customer(String guid) {
		super(guid);
	}

	public static Customer fromJson(JsonNode node) {
		Customer customer = new Customer(node.get("guid").asText());
		customer.name = node.get("name").asText();
		customer.type = node.get("type").asText();
		customer.location = Location.fromJson(node.get("location"));
		return customer;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Location getLocation() {
		return location;
	}

}
