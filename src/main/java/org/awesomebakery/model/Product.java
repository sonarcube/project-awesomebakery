package org.awesomebakery.model;

import com.fasterxml.jackson.databind.JsonNode;

public class Product {

	private String id;
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

	
	public static Product fromJson(JsonNode node) {
		Product product= new Product();
		product.id = node.get("id").asText();
		product.doughPrepTime = node.get("dough-prep-time").asInt();
		product.restingPeriod = node.get("resting-time").asInt();
		product.itemPrepTime = node.get("item-prep-time").asInt();
		product.itemsFitInOvenSlot = node.get("number-fitting-in-oven-slot").asInt();
		product.bakingTime = node.get("baking-time").asInt();
		product.bakingTemperature = node.get("baking-temp").asInt();
		product.boxingTemperature = node.get("boxing-temp").asInt();
		product.coolingTimeFactor = node.get("cooling-time-factor").asInt();
		product.itemsFitInBox = node.get("items-fitting-in-box").asInt();
		product.productionCost = node.get("production-price").asInt();
		product.salesPrice = node.get("sales-price").asInt();
		return product;
	}

	public String getId() {
		return id;
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
