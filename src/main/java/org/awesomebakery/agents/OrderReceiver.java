package org.awesomebakery.agents;

import jade.lang.acl.ACLMessage;

public interface OrderReceiver {
	public void onOrderReceived(ACLMessage message);
}