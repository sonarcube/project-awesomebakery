package org.awesomebakery.agents;

import org.awesomebakery.behaviors.TakeOrderBehavior;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public abstract class ServiceAgent extends Agent implements OrderReceiver {

	private static final long serialVersionUID = -1533825643866888846L;

	protected final String name;

	public ServiceAgent(String name) {
		this.name = name;
	}

	public abstract String getServiceType();

	@Override
	protected void setup() {
		registerService();
	}

	protected void registerService() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType(getServiceType());
		sd.setName(name);
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO handle
			e.printStackTrace();
		}
		addBehaviour(new TakeOrderBehavior(this));
	}

}
