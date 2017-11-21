package org.awesomebakery.agents;

public class OvenManager extends ManagerAgent {
	private static final long serialVersionUID = 6874014530350418319L;
	public static final String SERVICE_TYPE = "OvenManager";

	public OvenManager(String name) {
		super(name);
	}

	@Override
	public String getServiceType() {
		return SERVICE_TYPE;
	}
}
