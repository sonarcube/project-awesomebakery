package org.awesomebakery.agents;

import org.awesomebakery.model.Location;

public class Customer {

	private String id;
	private Location location;

	public Customer(String id, Location location) {
		this.id = id;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}
