package org.awesomebakery.agents;

import java.util.List;
import java.util.Vector;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Cashier extends Agent {

	private static final long serialVersionUID = 1L;
	private List<String> orders = new Vector<>();

	protected void setup() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("cashier");
		sd.setName("cashier");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			// TODO handle
			e.printStackTrace();
		}
		addBehaviour(new OrderServer());
	}

	private class OrderServer extends CyclicBehaviour {

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
				System.out.println(orders);
			} else {
				block();
			}
		}

	}
}
