package org.awesomebakery.agents;

import jade.lang.acl.ACLMessage;

public class DoughRester extends ServiceAgent {
	private static final long serialVersionUID = 3213192460512915864L;

	public static final String SERVICE_TYPE = "DoughRester";

	public DoughRester(String name) {
		super(name);
	}

	@Override
	public String getServiceType() {
		return SERVICE_TYPE;
	}

	@Override
	public void onMessageReceived(ACLMessage message) {
		// TODO Auto-generated method stub

	}
}
