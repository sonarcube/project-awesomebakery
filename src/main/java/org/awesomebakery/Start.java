package org.awesomebakery;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.awesomebakery.model.Scenario;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jade.util.ObjectManager;

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
    	List<String> agents = new Vector<>();
    	agents.add("bakery:org.awesomebakery.agents.Factory");
    	agents.add("customer:org.awesomebakery.agents.Customer");


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
