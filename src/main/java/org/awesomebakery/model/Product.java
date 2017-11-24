package org.awesomebakery.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Product extends Entity {

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

	public Product(String guid) {
		super(guid);
	}

	public static Product fromJson(JsonNode node) {
		Product product = new Product(node.get("guid").asText());
		product.doughPrepTime = node.get("dough_prep_time").asInt();
		product.restingPeriod = node.get("resting_time").asInt();
		product.itemPrepTime = node.get("item_prep_time").asInt();
		product.itemsFitInOvenSlot = node.get("breads_per_oven").asInt();
		product.bakingTime = node.get("baking_time").asInt();
		product.bakingTemperature = node.get("baking_temp").asInt();
		product.boxingTemperature = node.get("boxing_temp").asInt();
		product.coolingTimeFactor = node.get("cooling_rate").asInt();
		product.itemsFitInBox = node.get("breads_per_box").asInt();
		product.productionCost = node.get("production_cost").asInt();
		product.salesPrice = node.get("sales_price").asInt();
		return product;
	}

	public int getDoughPrepTime() {
		return doughPrepTime;
	}

	public int getRestingPeriod() {
		return restingPeriod;
	}

	public int getItemPrepTime() {
		return itemPrepTime;
	}

	public int getItemsFitInOvenSlot() {
		return itemsFitInOvenSlot;
	}

	public int getBakingTime() {
		return bakingTime;
	}

	public int getBakingTemperature() {
		return bakingTemperature;
	}

	public int getBoxingTemperature() {
		return boxingTemperature;
	}

	public int getCoolingTimeFactor() {
		return coolingTimeFactor;
	}

	public int getItemsFitInBox() {
		return itemsFitInBox;
	}

	public int getProductionCost() {
		return productionCost;
	}

	public int getSalesPrice() {
		return salesPrice;
	}

}
