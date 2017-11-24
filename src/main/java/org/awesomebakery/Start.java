package org.awesomebakery;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.awesomebakery.agents.*;
import org.awesomebakery.agents.managers.KneadingManager;
import org.awesomebakery.agents.managers.OvenManager;
import org.awesomebakery.model.Order;
import org.awesomebakery.model.Scenario;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;

public class Start {
	public static void main(String[] args) throws Throwable {
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		runtime.setCloseVM(true);
		Properties profileProperties = new ExtendedProperties();
		profileProperties.put(Profile.MAIN, true);
		profileProperties.put(Profile.GUI, false);
		Profile profile = new ProfileImpl(profileProperties);
		AgentContainer mainContainer = runtime.createMainContainer(profile);
		
		File file = new File("src/main/config/random-scenario.json");
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node;
		try {
			node = objectMapper.readTree(file);
		} catch (Throwable t) {
			System.err.println("Failed to read Scenario file " + file.getAbsolutePath());
			throw t;
		}

		Scenario scenario = Scenario.fromJson(node);
		
		Map<String, List<Order>> customerOrders = new HashMap<>();
		for (Order order : scenario.getOrders()) {
			if (!customerOrders.containsKey(order.getCustomerId())) {
				customerOrders.put(order.getCustomerId(), new ArrayList<Order>());
			}
			customerOrders.get(order.getCustomerId()).add(order);
		}

		for (org.awesomebakery.model.Customer customer : scenario.getCustomers()) {
			Customer agent = new Customer(customerOrders.get(customer.getGuid()));
			mainContainer.acceptNewAgent(customer.getName(), agent).start();
		}
		
		for(org.awesomebakery.model.Bakery bakery : scenario.getBakeries()) {
			Bakery agent = new Bakery(bakery.getName());
			mainContainer.acceptNewAgent(bakery.getName(), agent).start();
		}
		KneadingManager kneadingManager = new KneadingManager("KneadingManager");
		mainContainer.acceptNewAgent("KneadingManager", kneadingManager).start();

		OvenManager ovenManager = new OvenManager("OvenManager");
		mainContainer.acceptNewAgent("OvenManager", ovenManager).start();

		DoughPreparer doughPreparer = new DoughPreparer("DoughPreparer");
		mainContainer.acceptNewAgent("DoughPreparer", doughPreparer).start();

		DoughRester doughRester = new DoughRester("DoughRester");
		mainContainer.acceptNewAgent("DoughRester", doughRester).start();
		mainContainer.start();
	}
}
