package org.awesomebakery.agents;

import org.awesomebakery.model.Location;

public class Truck {

	private String id;
	private Location location;
	private int maxNumberBoxes;
	private int[] boxVector;

	public Truck(String id, Location location, int maxNumberBoxes, int[] boxVector) {
		this.id = id;
		this.location = location;
		this.maxNumberBoxes = maxNumberBoxes;
		this.boxVector = boxVector;
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

	public int getMaxNumberBoxes() {
		return maxNumberBoxes;
	}

	public void setMaxNumberBoxes(int maxNumberBoxes) {
		this.maxNumberBoxes = maxNumberBoxes;
	}

	public int[] getBoxVector() {
		return boxVector;
	}

	public void setBoxVector(int[] boxVector) {
		this.boxVector = boxVector;
	}
}
