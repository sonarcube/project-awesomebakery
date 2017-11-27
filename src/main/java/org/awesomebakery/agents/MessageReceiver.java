package org.awesomebakery.agents;

import jade.lang.acl.ACLMessage;
import jade.util.leap.Serializable;

public interface MessageReceiver extends Serializable {
	public void onMessageReceived(ACLMessage message);
}