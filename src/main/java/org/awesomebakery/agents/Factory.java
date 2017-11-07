package org.awesomebakery.agents;

import org.awesomebakery.model.Location;

public class Factory {

	private Location location;
	private int numberDoughKneadingMachines;
	private int numberOvens;
	private int ovenHeatingTimeFactor;
	private int ovenCoolingTimeFactor;

	public Factory(Location location, int numberDoughKneadingMachines, int numberOvens, int ovenHeatingTimeFactor,
			int ovenCoolingTimeFactor) {
		this.location = location;
		this.numberDoughKneadingMachines = numberDoughKneadingMachines;
		this.numberOvens = numberOvens;
		this.ovenHeatingTimeFactor = ovenHeatingTimeFactor;
		this.ovenCoolingTimeFactor = ovenCoolingTimeFactor;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getNumberDoughKneadingMachines() {
		return numberDoughKneadingMachines;
	}

	public void setNumberDoughKneadingMachines(int numberDoughKneadingMachines) {
		this.numberDoughKneadingMachines = numberDoughKneadingMachines;
	}

	public int getNumberOvens() {
		return numberOvens;
	}

	public void setNumberOvens(int numberOvens) {
		this.numberOvens = numberOvens;
	}

	public int getOvenHeatingTimeFactor() {
		return ovenHeatingTimeFactor;
	}

	public void setOvenHeatingTimeFactor(int ovenHeatingTimeFactor) {
		this.ovenHeatingTimeFactor = ovenHeatingTimeFactor;
	}

	public int getOvenCoolingTimeFactor() {
		return ovenCoolingTimeFactor;
	}

	public void setOvenCoolingTimeFactor(int ovenCoolingTimeFactor) {
		this.ovenCoolingTimeFactor = ovenCoolingTimeFactor;
	}

}
