package org.awesomebakery.model;

public class Date {

	private int day;
	private int hour;

	public Date(int day, int hour) {
		this.day = day;
		this.hour = hour;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	@Override
	public String toString() {
		return "<" + day + "." + hour + ">";
	}

}
