package org.awesomebakery.agents;

import java.util.List;

import org.awesomebakery.behaviors.FindAgentBehaviour;
import org.awesomebakery.model.Order;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class Customer extends Agent {

	private static final long serialVersionUID = 2900621781398066801L;
	private final List<Order> orders;
	private List<AID> bakeries;

	public Customer(List<Order> orders) {
		this.orders = orders;
	}

	protected void setup() {
		addBehaviour(new FindAgentBehaviour(Bakery.SERVICE_TYPE, foundAgents -> {
			bakeries = foundAgents;
			if (orders != null) {
				for (Order order : orders) {
					// TODO change behavior for requesting orders delayed
					addBehaviour(new PlaceOrderBehaviour(order));
				}
			}
		}));
	}

	private class PlaceOrderBehaviour extends OneShotBehaviour {

		private static final long serialVersionUID = 4800096756945729705L;
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
		}
	}
}
