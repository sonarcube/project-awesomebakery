package org.awesomebakery.agents.managers;

import org.awesomebakery.agents.ServiceAgent;

import jade.lang.acl.ACLMessage;

public abstract class ManagerAgent extends ServiceAgent {

	private static final long serialVersionUID = -4442460159091041848L;

	public ManagerAgent(String name) {
		super(name);
	}

	@Override
	public void onOrderReceived(ACLMessage message) {
		// TODO Auto-generated method stub
	}

}
