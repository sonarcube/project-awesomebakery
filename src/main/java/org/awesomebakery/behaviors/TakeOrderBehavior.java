package org.awesomebakery.behaviors;

import org.awesomebakery.agents.OrderReceiver;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class TakeOrderBehavior extends CyclicBehaviour {

	private static final long serialVersionUID = -3085853532979822608L;
	private OrderReceiver orderReceiver;

	public TakeOrderBehavior(OrderReceiver orderReceiver) {
		this.orderReceiver = orderReceiver;
	}

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			myAgent.addBehaviour(new OneShotBehaviour() {

				private static final long serialVersionUID = -2682097323876790677L;

				@Override
				public void action() {
					orderReceiver.onOrderReceived(msg);
				}
			});

		} else {
			block();
		}
	}

}
