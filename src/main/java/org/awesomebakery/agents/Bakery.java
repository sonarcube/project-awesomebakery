package org.awesomebakery.agents;

import java.util.ArrayList;
import java.util.List;

import org.awesomebakery.agents.managers.KneadingManager;
import org.awesomebakery.agents.managers.OvenManager;
import org.awesomebakery.behaviors.FindAgentBehaviour;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class Bakery extends ServiceAgent {

	private static final long serialVersionUID = -6932447484983683304L;

	public static final String SERVICE_TYPE = "bakery";

	private List<AID> productionParticipants;

	public Bakery(String name) {
		super(name);
	}

	private void findProductionParticipant(String serviceType, int position) {
		productionParticipants.add(position, null);
		FindAgentBehaviour findBehavior = new FindAgentBehaviour(serviceType, foundAgents -> {
			productionParticipants.set(position, foundAgents.get(0));
			for (AID participant : productionParticipants) {
				if (participant == null) {
					return;
				}
			}
			registerService();
		});
		addBehaviour(findBehavior);
	}

	protected void setup() {
		productionParticipants = new ArrayList<>();
		findProductionParticipant(KneadingManager.SERVICE_TYPE, 0);
		findProductionParticipant(DoughRester.SERVICE_TYPE, 1);
		findProductionParticipant(DoughPreparer.SERVICE_TYPE, 2);
		findProductionParticipant(OvenManager.SERVICE_TYPE, 3);
	}

	@Override
	public String getServiceType() {
		return SERVICE_TYPE;
	}

	@Override
	public void onOrderReceived(ACLMessage message) {
		System.out.println("I got this order " + message.getContent());
	}
}
