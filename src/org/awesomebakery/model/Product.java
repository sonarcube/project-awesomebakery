package org.awesomebakery.model;

public class Product {

	private String productId;
	private int doughPrepTime;
	private int restingPeriod;
	private int itemPrepTime;
	private int itemsFitInOvenSlot;
	private int bakingTime;
	private int bakingTemperature;
	private int boxingTemperature;
	private int coolingTimeFactor;
	private int itemsFitInBox;
	private int productionCost;
	private int salesPrice;

	public Product(String productId, int doughPrepTime, int restingPeriod, int itemPrepTime, int itemsFitInOvenSlot,
			int bakingTime, int bakingTemperature, int boxingTemperature, int coolingTimeFactor, int itemsFitInBox,
			int productionCost, int salesPrice) {
		this.productId = productId;
		this.doughPrepTime = doughPrepTime;
		this.restingPeriod = restingPeriod;
		this.itemPrepTime = itemPrepTime;
		this.itemsFitInOvenSlot = itemsFitInOvenSlot;
		this.bakingTime = bakingTime;
		this.bakingTemperature = bakingTemperature;
		this.boxingTemperature = boxingTemperature;
		this.coolingTimeFactor = coolingTimeFactor;
		this.itemsFitInBox = itemsFitInBox;
		this.productionCost = productionCost;
		this.salesPrice = salesPrice;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getDoughPrepTime() {
		return doughPrepTime;
	}

	public void setDoughPrepTime(int doughPrepTime) {
		this.doughPrepTime = doughPrepTime;
	}

	public int getRestingPeriod() {
		return restingPeriod;
	}

	public void setRestingPeriod(int restingPeriod) {
		this.restingPeriod = restingPeriod;
	}

	public int getItemPrepTime() {
		return itemPrepTime;
	}

	public void setItemPrepTime(int itemPrepTime) {
		this.itemPrepTime = itemPrepTime;
	}

	public int getItemsFitInOvenSlot() {
		return itemsFitInOvenSlot;
	}

	public void setItemsFitInOvenSlot(int itemsFitInOvenSlot) {
		this.itemsFitInOvenSlot = itemsFitInOvenSlot;
	}

	public int getBakingTime() {
		return bakingTime;
	}

	public void setBakingTime(int bakingTime) {
		this.bakingTime = bakingTime;
	}

	public int getBakingTemperature() {
		return bakingTemperature;
	}

	public void setBakingTemperature(int bakingTemperature) {
		this.bakingTemperature = bakingTemperature;
	}

	public int getBoxingTemperature() {
		return boxingTemperature;
	}

	public void setBoxingTemperature(int boxingTemperature) {
		this.boxingTemperature = boxingTemperature;
	}

	public int getCoolingTimeFactor() {
		return coolingTimeFactor;
	}

	public void setCoolingTimeFactor(int coolingTimeFactor) {
		this.coolingTimeFactor = coolingTimeFactor;
	}

	public int getItemsFitInBox() {
		return itemsFitInBox;
	}

	public void setItemsFitInBox(int itemsFitInBox) {
		this.itemsFitInBox = itemsFitInBox;
	}

	public int getProductionCost() {
		return productionCost;
	}

	public void setProductionCost(int productionCost) {
		this.productionCost = productionCost;
	}

	public int getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(int salesPrice) {
		this.salesPrice = salesPrice;
	}

}
