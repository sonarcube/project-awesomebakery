package org.awesomebakery.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Date {

	private int day;
	private int hour;

	public Date() {
	}

	public Date(int day, int hour) {
		this.day = day;
		this.hour = hour;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	@Override
	public String toString() {
		return "<" + day + "." + hour + ">";
	}

	public static Date fromJson(JsonNode node) {
		Date date = new Date();
		date.day = node.get("day").asInt();
		date.hour = node.get("hour").asInt();
		return date;
	}

}
