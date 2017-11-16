package org.awesomebakery.model;

import java.util.Collections;
import java.util.List;

import org.awesomebakery.utils.JsonListHelper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class Bakery extends Entity {
	private String name;
	private Location location;
	private int kneadingMachines;
	private int doughPrepTables;
	private List<Oven> ovens;
	private List<Product> products;
	private List<Truck> trucks;

	public Bakery(String guid) {
		super(guid);
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return location;
	}

	public int getKneadingMachines() {
		return kneadingMachines;
	}

	public int getDoughPrepTables() {
		return doughPrepTables;
	}

	public List<Oven> getOvens() {
		return Collections.unmodifiableList(ovens);
	}

	public List<Product> getProducts() {
		return Collections.unmodifiableList(products);
	}

	public List<Truck> getTrucks() {
		return Collections.unmodifiableList(trucks);
	}

	public static Bakery fromJson(JsonNode node) {
		Bakery bakery = new Bakery(node.get("guid").asText());
		bakery.name = node.get("name").asText();
		bakery.kneadingMachines = node.get("kneading_machines").asInt();
		bakery.doughPrepTables = node.get("dough_prep_tables").asInt();
		bakery.ovens = JsonListHelper.toObjectList(Oven.class, (ArrayNode) node.get("ovens"));
		bakery.products = JsonListHelper.toObjectList(Product.class, (ArrayNode) node.get("products"));
		bakery.trucks = JsonListHelper.toObjectList(Truck.class, (ArrayNode) node.get("trucks"));
		return bakery;
	}

}
