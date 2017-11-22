package org.awesomebakery.agents;

import jade.lang.acl.ACLMessage;

public interface MessageReceiver {
	public void onMessageReceived(ACLMessage message);
}