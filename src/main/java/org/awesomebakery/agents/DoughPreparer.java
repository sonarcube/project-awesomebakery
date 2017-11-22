package org.awesomebakery.agents;

import jade.lang.acl.ACLMessage;

public class DoughPreparer extends ServiceAgent {
	private static final long serialVersionUID = -7194206594373177352L;

	public static final String SERVICE_TYPE = "DoughPreparer";

	public DoughPreparer(String name) {
		super(name);
	}

	@Override
	public String getServiceType() {
		return SERVICE_TYPE;
	}

	@Override
	public void onOrderReceived(ACLMessage message) {
		// TODO Auto-generated method stub

	}
}
