package org.awesomebakery.agents;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import org.awesomebakery.model.Order;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Customer extends Agent {

	private final List<Order> orders;
	private List<AID> bakeries;

	public Customer(List<Order> orders) {
		this.orders = orders;
	}

	protected void setup() {
		if (orders != null && !orders.isEmpty()) {
			addBehaviour(new FindBakeryBehaviour());
		}
	}

	private class FindBakeryBehaviour extends Behaviour {
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			DFAgentDescription template = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType(Bakery.SERVICE_TYPE);
			template.addServices(sd);
			if (bakeries == null) {
				bakeries = new ArrayList<>();
			}
			try {
				DFAgentDescription[] result = DFService.search(myAgent, template);
				for (DFAgentDescription d : result) {
					bakeries.add(d.getName());
				}
			} catch (FIPAException fe) {
				fe.printStackTrace();
			}
			if (!bakeries.isEmpty()) {
				for (Order order : orders) {
					myAgent.addBehaviour(new PlaceOrderBehaviour(order));
				}
			}

		}

		@Override
		public boolean done() {
			return !bakeries.isEmpty();
		}

	}

	private MessageTemplate mt = null;

	private class PlaceOrderBehaviour extends OneShotBehaviour {
		private static final long serialVersionUID = 1L;

		private Order order;

		public PlaceOrderBehaviour(Order order) {
			this.order = order;
		}

		@Override
		public void action() {
			ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
			for (AID bakery : bakeries) {
				cfp.addReceiver(bakery);
			}
			cfp.setContent(order.toString());
			cfp.setConversationId("request-price-for" + order.getGuid());
			cfp.setReplyWith("cfp" + System.currentTimeMillis());
			myAgent.send(cfp);
			mt = MessageTemplate.and(MessageTemplate.MatchConversationId(cfp.getConversationId()),
					MessageTemplate.MatchInReplyTo(cfp.getReplyWith()));
			myAgent.addBehaviour(new CheckResponseBehaviour());
		}

	}

	private class CheckResponseBehaviour extends Behaviour {
		private static final long serialVersionUID = 1L;
		boolean done = false;

		@Override
		public void action() {
			ACLMessage reply = myAgent.receive(mt);
			if (reply != null) {
				if (reply.getPerformative() == ACLMessage.ACCEPT_PROPOSAL) {
					// Good, nothing to do. exit
					myAgent.doDelete();
				} else {
					// Failed, retry next time.
					System.out.println("failed");
					myAgent.addBehaviour(new FindBakeryBehaviour());
					if (done()) {
						myAgent.doDelete();
					}
				}
			} else {
				block();
			}
		}

		@Override
		public boolean done() {
			return done = true;
		}

	}
}
