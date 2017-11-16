package org.awesomebakery.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Truck extends Entity {

	private int loadCapacity;
	private Location location;

	public Truck(String guid) {
		super(guid);
	}

	public static Truck fromJson(JsonNode node) {
		Truck truck = new Truck(node.get("guid").asText());
		truck.loadCapacity = node.get("load_capacity").asInt();
		truck.location = Location.fromJson(node.get("location"));
		return truck;
	}

	public int getLoadCapacity() {
		return loadCapacity;
	}

	public Location getLocation() {
		return location;
	}

}
