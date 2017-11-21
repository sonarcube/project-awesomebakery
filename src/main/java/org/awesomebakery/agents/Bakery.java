package org.awesomebakery.agents;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.awesomebakery.behaviors.FindAgentBehaviour;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Bakery extends Agent {

	private static final long serialVersionUID = -6932447484983683304L;

	public static final String SERVICE_TYPE = "bakery";
	
	private List<String> orders = new Vector<>();
	private List<AID> productionParticipants;
	private String name;


	public Bakery(String name) {
		this.name = name;
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
			registerTakeOrderBehavior();
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

	private void registerTakeOrderBehavior() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType(SERVICE_TYPE);
		sd.setName(name);
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO handle
			e.printStackTrace();
		}
		addBehaviour(new Bakery.TakeOrder());
	}

	private class TakeOrder extends CyclicBehaviour {
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
			ACLMessage msg = myAgent.receive(mt);
			if (msg != null) {
				String name = msg.getContent();
				ACLMessage reply = msg.createReply();
				orders.add(name);
				reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
				myAgent.send(reply);
				System.out.println("I got this Order "+orders);
			} else {
				block();
			}
		}
	}
}
