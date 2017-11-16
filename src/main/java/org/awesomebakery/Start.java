package org.awesomebakery;

import org.awesomebakery.agents.Customer;
import org.awesomebakery.agents.Factory;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.ControllerException;

public class Start {
	public static void main(String[] args) throws ControllerException {
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		runtime.setCloseVM(true);
		Properties profileProperties = new ExtendedProperties();
		profileProperties.put(Profile.MAIN, true);
		profileProperties.put(Profile.GUI, false);
		Profile profile = new ProfileImpl(profileProperties);
		AgentContainer mainContainer = runtime.createMainContainer(profile);
		mainContainer.acceptNewAgent("bakery", new Factory()).start();
		mainContainer.acceptNewAgent("customer", new Customer()).start();
		mainContainer.start();
	}
}
