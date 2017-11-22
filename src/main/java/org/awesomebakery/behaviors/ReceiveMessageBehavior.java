package org.awesomebakery.behaviors;


import org.awesomebakery.agents.MessageReceiver;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ReceiveMessageBehavior extends CyclicBehaviour {

	private static final long serialVersionUID = -3085853532979822608L;
	private MessageReceiver messageReceiver;

	public ReceiveMessageBehavior(MessageReceiver messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			messageReceiver.onMessageReceived(msg);
		} else {
			block();
		}
	}

}
