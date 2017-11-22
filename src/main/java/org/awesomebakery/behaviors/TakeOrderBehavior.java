package org.awesomebakery.behaviors;


import org.awesomebakery.agents.OrderReceiver;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class TakeOrderBehavior extends CyclicBehaviour {

	private static final long serialVersionUID = -3085853532979822608L;
	private OrderReceiver orderReceiver;

	public TakeOrderBehavior(OrderReceiver orderReceiver) {
		this.orderReceiver = orderReceiver;
	}

	@Override
	public void action() {
		MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);
		ACLMessage msg = myAgent.receive(mt);
		if (msg != null) {
			orderReceiver.onOrderReceived(msg);
		} else {
			block();
		}
	}

}
