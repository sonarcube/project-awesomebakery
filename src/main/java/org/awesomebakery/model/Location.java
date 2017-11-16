package org.awesomebakery.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Location {

	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "<" + x + "," + y + ">";
	}

	public static Location fromJson(JsonNode node) {
		Location location = new Location();
		location.x = node.get("x").asInt();
		location.y = node.get("y").asInt();
		return location;
	}

}
