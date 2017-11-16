package org.awesomebakery.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Oven extends Entity {

	private int coolingRate;
	private int heatingRate;

	public Oven(String guid) {
		super(guid);
	}

	public static Oven fromJson(JsonNode node) {
		Oven oven = new Oven(node.get("guid").asText());
		oven.coolingRate = node.get("cooling_rate").asInt();
		oven.heatingRate = node.get("heating_rate").asInt();
		return oven;
	}

	public int getCoolingRate() {
		return coolingRate;
	}

	public int getHeatingRate() {
		return heatingRate;
	}
}
