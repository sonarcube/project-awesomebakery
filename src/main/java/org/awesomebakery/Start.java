package org.awesomebakery;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.awesomebakery.model.Order;
import org.awesomebakery.model.Scenario;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Start {
	public static void main(String[] args) throws Throwable {

		File file = new File("scenarios/00/scenario.json");

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

		List<String> agents = new Vector<>();
		agents.add("bakery:org.awesomebakery.agents.Factory");
		for (String customerId : customerOrders.keySet()) {
			agents.add(customerId + ":org.awesomebakery.agents.Customer(" + customerOrders.get(customerId).toString() + ")");
		}
		System.out.println("These agents are started");
		System.out.println(agents);

		List<String> cmd = new Vector<>();
		cmd.add("-agents");
		StringBuilder sb = new StringBuilder();
		for (String a : agents) {
			sb.append(a);
			sb.append(";");
		}
		cmd.add(sb.toString());
		jade.Boot.main(cmd.toArray(new String[cmd.size()]));
	}
}
